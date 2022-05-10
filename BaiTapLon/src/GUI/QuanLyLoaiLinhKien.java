package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ConnectionDB.ConnectDB;
import dao.LoaiLinhKien_DAO;
import Entity.HoaDon;
import Entity.LoaiLinhKien;

public class QuanLyLoaiLinhKien extends JFrame implements ActionListener, MouseListener{
	
	private JTable table;
	private DefaultTableModel model;
	
	private JLabel lblMa, lblTen;
	private JTextField txtMa, txtTen;
	
	private JButton bttTim;
	private JButton bttThem;
	private JButton bttSua;
	private JButton bttXoa;
	private JButton bttLuu;
	private JButton bttXoaTrang;
	private JButton bttThoat;
	
	private LoaiLinhKien_DAO llk_dao;
	public QuanLyLoaiLinhKien () {
		try {
			ConnectDB.getInstance().connect();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		llk_dao = new LoaiLinhKien_DAO();
		this.setSize(1200, 800);
		this.setTitle("Quản Lý Linh Kiện");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JPanel pnNorth1 = new JPanel();
		JLabel lblTieuDe;
		lblTieuDe = new JLabel("THÔNG TIN LOẠI LINH KIỆN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);
		pnNorth1.add(lblTieuDe);
        pnNorth.add(pnNorth1,BorderLayout.NORTH);
		
        TitledBorder tileAnh = new TitledBorder("Nơi để ảnh");
		JPanel pnWest1 = new JPanel();
        pnWest1.setLayout(new BorderLayout());
        pnWest1.setBorder(tileAnh);
        pnWest1.setPreferredSize(new Dimension(200,200));
        JLabel lbImage = new JLabel();
        lbImage.setPreferredSize(new Dimension(130,120));
        pnWest1.add(lbImage,BorderLayout.NORTH);

        JPanel pnCenter1 = new JPanel();
        Box b,b1;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840,70));

        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());

        b1.add(lblMa = new JLabel("Mã loại linh kiện: "));
        b1.add(txtMa = new JTextField());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên loại linh kiện:    "));
        b1.add(txtTen = new JTextField());
        b.add(Box.createVerticalStrut(10));
        
        TitledBorder tileTacVu = new TitledBorder("Tác vụ");
		JPanel pnSouth1 = new JPanel();
		pnSouth1.setPreferredSize(new Dimension(1030,70));
		pnSouth1.setBorder(tileTacVu);
        
        pnSouth1.add(bttTim = new JButton("Tìm"));
        pnSouth1.add(bttThem = new JButton("Thêm"));
        pnSouth1.add(bttSua = new JButton("Sửa"));
        pnSouth1.add(bttXoa = new JButton("Xóa"));
        pnSouth1.add(bttXoaTrang = new JButton("Xóa trắng"));
        pnSouth1.add(bttLuu = new JButton("Lưu"));
        pnSouth1.add(bttThoat = new JButton("Thoát"));
        
        lblMa.setPreferredSize(lblTen.getPreferredSize());
        
        pnCenter1.add(b);
        pnNorth.add(pnWest1,BorderLayout.WEST);
        pnNorth.add(pnCenter1,BorderLayout.CENTER);
        pnNorth.add(pnSouth1,BorderLayout.SOUTH);
		
		
		TitledBorder tileDanhSach = new TitledBorder("Danh sách loại linh kiện");
		JPanel pnCenter = new JPanel();
        pnCenter.setBorder(tileDanhSach);
        dao.LoaiLinhKien_DAO llkDao = new dao.LoaiLinhKien_DAO();
        String [] headers = {"Mã Loại Linh Kiện", "Tên Loại Linh Kiện"};
        model = new DefaultTableModel(headers, 0);
        table = new JTable(model);

        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(930,230));
        pnCenter.add(sc);
        
		this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        
        bttThem.addActionListener(this);
		bttSua.addActionListener(this);
		bttLuu.addActionListener(this);
		bttThoat.addActionListener(this);
		bttTim.addActionListener(this);
		bttXoa.addActionListener(this);
		bttXoaTrang.addActionListener(this);
		table.addMouseListener(this);
		setWhenEditField(false);
		bttLuu.setEnabled(false);
		loadLLKToTable();
	}
	
	public static void main(String[] args) {
		new QuanLyLoaiLinhKien().setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				sendDataToTxt(row);
			}
		}
	}
	
	public void sendDataToTxt(int row) {
		txtMa.setText(table.getValueAt(row, 0).toString());
		txtTen.setText(table.getValueAt(row, 1).toString());
	}
	
	public void setWhenAddData(Boolean b) {
		txtMa.setEditable(b);
		txtTen.setEditable(b);
	}
	
	public void setWhenEditField(Boolean b) {
		txtMa.setEditable(false);
		txtTen.setEditable(false);
	}
	public void loadLLKToTable() {
		while (table.getRowCount() != 0) {
			model.removeRow(0);
		}
		ArrayList<LoaiLinhKien> dsLLK = (ArrayList<LoaiLinhKien>) llk_dao.getAllLoaiLinhKien();
		for (LoaiLinhKien llk : dsLLK) {
			String data[] = {llk.getMaloai(), llk.getTenLinhKien()};
			model.addRow(data);
		}
		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(1, 1);
			sendDataToTxt(0);
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(bttThem)) {
			if (bttThem.getText().equalsIgnoreCase("Thêm")) {
				bttThem.setText("Hủy");
				bttSua.setEnabled(false);
				bttLuu.setEnabled(true);
				bttXoa.setEnabled(false);
				bttTim.setEnabled(false);
				setWhenAddData(true);
			} else if (bttThem.getText().equalsIgnoreCase("Hủy")) {
				bttThem.setText("Thêm");
				bttSua.setEnabled(true);
				bttLuu.setEnabled(false);
				bttXoa.setEnabled(true);
				bttTim.setEnabled(true);
				setWhenAddData(false);
			}
		} else if(o.equals(bttSua)) {
			if (bttSua.getText().equalsIgnoreCase("Sửa")) {
				setWhenEditField(true);
				bttSua.setText("Hủy");
				bttThem.setEnabled(false);
				bttLuu.setEnabled(true);
				bttXoa.setEnabled(false);
				bttTim.setEnabled(false);
			} else if (bttSua.getText().equalsIgnoreCase("Hủy")) {
				bttSua.setText("Sửa");
				setWhenEditField(false);
				bttThem.setEnabled(true);
				bttLuu.setEnabled(false);
				bttXoa.setEnabled(true);
				bttTim.setEnabled(true);
				
			}
		} else if (o.equals(bttThoat)) {
			int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát");
			if (isYes == 0) {
				System.exit(0);
			}
		} else if (o.equals(bttXoaTrang)) {
			txtMa.setText("");
			txtTen.setText("");
		} else if (o.equals(bttXoa)) {
			int rowSeleted = table.getSelectedRow();
			if (rowSeleted != -1) {
				int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hay không");
				if (isYes == 0) {
					String ma = table.getValueAt(rowSeleted, 0).toString();
					boolean isDel = llk_dao.deleteLLK(ma);
					if (isDel) {
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
						loadLLKToTable();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
					
				}
			} else {
				JOptionPane.showMessageDialog(null,  "Bạn phải chọn vào dòng cần xóa","Thông Báo", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (o.equals(bttLuu)) {
			if (bttThem.getText().equalsIgnoreCase("Hủy")) {
				String ma = txtMa.getText();
				String ten = txtTen.getText();
				LoaiLinhKien llk = new LoaiLinhKien(ma,ten);
				boolean isAdded = llk_dao.addLoaiLinhKien(llk);
				if (!isAdded) {
					JOptionPane.showMessageDialog(null, "Thêm vào thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Thêm vào thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					loadLLKToTable();
				}
				bttThem.setText("Thêm");
				bttXoa.setEnabled(true);
				bttSua.setEnabled(true);
				bttTim.setEnabled(true);
			} else if (bttSua.getText().equalsIgnoreCase("Hủy")) {
				String ma = txtMa.getText();
				String ten = txtTen.getText();
				LoaiLinhKien llk = new LoaiLinhKien(ma, ten);
				boolean isModifield = llk_dao.updateLoaiLK(llk);
				if (isModifield) {
					JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					loadLLKToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Sửa thất bại", "Thống báo", JOptionPane.ERROR_MESSAGE);
				}
				bttSua.setText("Sửa");
				bttThem.setEnabled(true);
				bttXoa.setEnabled(true);
				bttTim.setEnabled(true);
				setWhenEditField(false);
			} 
		} else if (o.equals(bttTim)) {
			int flag = 0;
			String ma = JOptionPane.showInputDialog(null, "Nhập vào mã loại linh kiện cần tìm");
			if (ma == null) {
				return;
			}
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.getValueAt(i, 0).toString().equalsIgnoreCase(ma)) {
					table.setRowSelectionInterval(i, i);
					sendDataToTxt(i);
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				JOptionPane.showMessageDialog(null, "Không có mã loại linh kiện này trong danh sách");
			}
		}
	}
}
