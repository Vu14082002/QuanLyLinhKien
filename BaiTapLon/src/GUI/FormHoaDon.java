package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.toedter.calendar.JDateChooser;

import dao.HoaDon_Dao;
import dao.LoaiLinhKien_DAO;
import Entity.HoaDon;
import ConnectionDB.ConnectDB;

public class FormHoaDon extends JFrame implements ActionListener, MouseListener,MenuListener{
	private LoaiLinhKien_DAO loaiLinhKien;
	private JMenu menuHome;
	private JMenu menuHeThong;
	private JMenu menuXuLy;
	private JMenu menuDanhMuc;
	private JMenu menuTimKiem;
	private JMenu menuThongKe;
	private JMenuBar menuBar;
	private DefaultMutableTreeNode rootNode;
	private JMenuItem menuLinhKien;
	private JMenuItem menuKhachHang;
	private JMenuItem menuNhanVien;
	private JMenuItem menuNhaCungCap;
	private JMenu menuUser;
	private String maNhanVien;
	private String tenNhanVien;
	private JMenuItem menuLoai;
	private JMenuItem menuHoaDon;
	private JMenuItem menuLoaiLinhKien;
	private JTree tree;
	private JPanel[] pnLinhKien;
	private JLabel[] lbma = new JLabel[100];
	private JLabel[] lbAnh=new JLabel[100];
	private JLabel[] lbTenLk=new JLabel[100];
	private JLabel[] lbSoLuong=new JLabel[100];
	private JLabel lbMaHoaDon, lbMaKhachHang, lbMaNhanVien, lbNgayDatHang, lbNgayGiaoHang, lbNgayChuyen, lbNoiNhan;
	private JTextField txtMaHD, txtMaKH, txtMaNV,  txtNoiNhan;
	private JDateChooser dateNgayGiaoHang, dateNgayDatHang, dateNgayChuyen;
	private JTable tableHoaDon;
	private DefaultTableModel modalTableHoaDon;
	private JButton btnThem, btnXoa, btnSua, btnXoaTrang, btnLuu, btnThoat, btnTimKiem;
	private dao.HoaDon_Dao hoaDon_dao;
	private AbstractButton menuQuanLyChiTietHoaDon;
	public FormHoaDon(String maNhanVien, String tenNhanVien) {
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		try {
			ConnectionDB.ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		hoaDon_dao = new dao.HoaDon_Dao();
		createFormHoaDon();
		loadHoaDonToTable();
		init();
	}
	public void init() {
		this.loaiLinhKien = new LoaiLinhKien_DAO();
		menuBar = new JMenuBar();
		menuHome = new JMenu("<html><p style='text-align:center; width:75px'>Trang chủ</p></html>");
		menuHeThong = new JMenu("<html><p style='text-align:center; width:75px'>Hệ thống</p></html>") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(this, "helo ");
			}
		};
		menuDanhMuc = new JMenu("<html><p style='text-align:center; width:75px'>Danh mục</p></html>");
		menuXuLy = new JMenu("<html><p style='text-align:center; width:75px'Xử lý</p></html>");
		menuThongKe = new JMenu("<html><p style='text-align:center; width:75px'>Thống kế</p></html>");
		menuTimKiem = new JMenu("<html><p style='text-align:center; width:75px'>Tìm kiếm</p></html>");
		menuUser = new JMenu("<html><p style='text-align:center; width:150px'>" + this.tenNhanVien + "</p></html>");
		menuBar.add(menuHome);
		menuBar.add(menuHeThong);
		menuBar.add(menuDanhMuc);
		menuBar.add(menuXuLy);
		menuBar.add(menuThongKe);
		menuBar.add(menuTimKiem);
		menuBar.add(menuUser);
		menuUser.setToolTipText("Admin");
		menuBar.setBackground(Color.decode("#00cec9"));
		menuBar.setPreferredSize(new Dimension(1200, 60));
		this.setJMenuBar(menuBar);
		Color color = Color.white;
		Font font = new Font("ARIAL", Font.BOLD, 14);
		menuHome.setForeground(color);
		menuHome.setFont(font);
		menuHeThong.setForeground(color);
		menuHeThong.setFont(font);
		menuDanhMuc.setForeground(color);
		menuDanhMuc.setFont(font);
		menuXuLy.setForeground(color);
		menuXuLy.setFont(font);
		menuThongKe.setForeground(color);
		menuThongKe.setFont(font);
		menuTimKiem.setForeground(color);
		menuTimKiem.setFont(font);
		// JmenuIte
		menuLinhKien = new JMenuItem("Quản lý linh kiện");
		menuLinhKien.setPreferredSize(new Dimension(200, 30));

		menuKhachHang = new JMenuItem("Quản lý khách hàng");
		menuKhachHang.setPreferredSize(new Dimension(200, 30));

		menuNhanVien = new JMenuItem("Quản lý nhân viên");
		menuNhanVien.setPreferredSize(new Dimension(200, 30));

		menuNhaCungCap = new JMenuItem("Danh sách nhà cung cấp");
		menuNhaCungCap.setPreferredSize(new Dimension(200, 30));

		menuHoaDon = new JMenuItem("Quản lý hoá đơn");
		menuHoaDon.setPreferredSize(new Dimension(200, 30));
		menuQuanLyChiTietHoaDon = new JMenuItem("Quản lý chi tiết hoá đơn");
		menuQuanLyChiTietHoaDon.setPreferredSize(new Dimension(200, 30));
		menuLoaiLinhKien = new JMenuItem("Danh sách loại linh kiện");
		menuLoaiLinhKien.setPreferredSize(new Dimension(200, 30));

		menuDanhMuc.add(menuKhachHang);
		menuDanhMuc.add(menuLinhKien);
		menuDanhMuc.add(menuNhanVien);
		menuDanhMuc.add(menuNhaCungCap);
		menuDanhMuc.add(menuHoaDon);
		menuDanhMuc.add(menuLoaiLinhKien);
		menuDanhMuc.add(menuQuanLyChiTietHoaDon);
		menuHome.setIcon(new ImageIcon(this.getClass().getResource("/Icon/homeIcon.png")));
		menuHeThong.setIcon(new ImageIcon(this.getClass().getResource("/Icon/systemIcon.png")));
		menuDanhMuc.setIcon(new ImageIcon(this.getClass().getResource("/Icon/danhMuc.png")));
		menuThongKe.setIcon(new ImageIcon(this.getClass().getResource("/Icon/thongKe.png")));
		menuTimKiem.setIcon(new ImageIcon(this.getClass().getResource("/Icon/searchIcon.png")));
		menuUser.setIcon(new ImageIcon(this.getClass().getResource("/Icon/User.png")));

		menuLinhKien.addActionListener(this);
		menuKhachHang.addActionListener(this);
		menuNhanVien.addActionListener(this);
		menuNhaCungCap.addActionListener(this);
		menuLoaiLinhKien.addActionListener(this);
		menuHoaDon.addActionListener(this);
		menuQuanLyChiTietHoaDon.addActionListener(this);

		menuHome.addMenuListener(this);
		menuHeThong.addMenuListener(this);
		menuThongKe.addMenuListener(this);
		menuUser.addMenuListener(this);

		// South
		JPanel pnMain = new JPanel();
		JLabel lb = new JLabel("<html> <div style=\"color: white; font-size: 14px; text-align: "
				+ "center; margin-top:5px;\"> &copy; Nhóm 8: quản lý linh kiên - Giảng viên hướng dẫn Tôn Long Phước "
				+ "</div> </html>\r\n");
		pnMain.add(lb);
		lb.setFont(new Font("arial", Font.BOLD, 18));
		pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
		pnMain.setPreferredSize(new Dimension(1200, 50));
		this.add(pnMain, BorderLayout.SOUTH);
		pnMain.setBackground(Color.decode("#00cec9"));
		TitledBorder title = new TitledBorder(BorderFactory.createLineBorder(Color.decode("#00cec9")));
		pnMain.setBorder(title);

	}

	public void South() {
		JPanel pnMain = new JPanel();
		JLabel lb = new JLabel("<html> <div style=\"color: white; font-size: 14px; text-align: "
				+ "center; margin-top:5px;\"> &copy; Nhóm 8: quản lý linh kiên - Giảng viên hướng dẫn Tôn Long Phước "
				+ "</div> </html>\r\n");
		pnMain.add(lb);
		lb.setFont(new Font("arial", Font.BOLD, 18));
		pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
		pnMain.setPreferredSize(new Dimension(1200, 50));
		this.add(pnMain, BorderLayout.SOUTH);
		pnMain.setBackground(Color.decode("#00cec9"));
		TitledBorder title = new TitledBorder(BorderFactory.createLineBorder(Color.decode("#00cec9")));
		pnMain.setBorder(title);
	}

	public void createFormHoaDon() {
		this.setSize(1200, 800);
		this.setTitle("Quản Lý Linh Kiện");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel labelHeader = new JLabel("Quản Lý Hóa Đơn");
		labelHeader.setForeground(Color.red);
		labelHeader.setFont(new Font("Arial", Font.BOLD, 20));
		JPanel panelContent = new JPanel();
		panelContent.add(labelHeader);
	
		this.add(panelContent, BorderLayout.CENTER);
		
		
		JPanel panelBody = new JPanel();
		panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));
		JPanel panelHD_KH = new JPanel();
		//panelHD_KH.setLayout(new BoxLayout(panelHD_KH, BoxLayout.X_AXIS));
		panelHD_KH.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelHD_KH.add(lbMaHoaDon = new JLabel("Mã Hóa Đơn: "));
		panelHD_KH.add(txtMaHD = new JTextField(30));
		panelHD_KH.add(Box.createHorizontalStrut(15));
		panelHD_KH.add(lbMaKhachHang = new JLabel("Mã Khách Hàng: "));
		panelHD_KH.add(txtMaKH = new JTextField(30));
		
		JPanel panelNV_ND = new JPanel();
		panelNV_ND.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelNV_ND.add(lbMaNhanVien = new JLabel("Mã Nhân Viên: "));
		panelNV_ND.add(txtMaNV = new JTextField(30));
		panelNV_ND.add(Box.createHorizontalStrut(15));
		panelNV_ND.add(lbNgayDatHang = new JLabel("Ngày Đặt Hàng: "));
		panelNV_ND.add(dateNgayDatHang = new JDateChooser());
		dateNgayDatHang.setDateFormatString("dd-MM-yyyy");
		dateNgayDatHang.setPreferredSize(new Dimension(305, 20));
		
		
		JPanel panelNG_NC = new JPanel();
		panelNG_NC.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelNG_NC.add(lbNgayGiaoHang = new JLabel("Ngày Giao Hàng: "));
		panelNG_NC.add(dateNgayGiaoHang = new JDateChooser());
		dateNgayGiaoHang.setPreferredSize(new Dimension(301, 20));
		dateNgayGiaoHang.setDateFormatString("dd-MM-yyyy");
		panelNG_NC.add(Box.createHorizontalStrut(15));
		panelNG_NC.add(lbNgayChuyen = new JLabel("Ngày Chuyển: "));
		panelNG_NC.add(dateNgayChuyen = new JDateChooser());
		dateNgayChuyen.setDateFormatString("dd-MM-yyyy");
		dateNgayChuyen.setPreferredSize(new Dimension(200, 20));
		dateNgayChuyen.setPreferredSize(new Dimension(305, 20));
		
		JPanel panelNN = new JPanel();
		panelNN.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelNN.add(lbNoiNhan = new JLabel("Nơi Nhận"));
		panelNN.add(txtNoiNhan = new JTextField(73));

		
		lbMaHoaDon.setPreferredSize(lbNgayGiaoHang.getPreferredSize());
		lbMaHoaDon.setAlignmentX(LEFT_ALIGNMENT);
		//lbNgayGiaoHang.setPreferredSize(lbMaKhachHang.getPreferredSize());
		lbNgayChuyen.setPreferredSize(lbNgayGiaoHang.getPreferredSize());
		lbMaNhanVien.setPreferredSize(lbNgayGiaoHang.getPreferredSize());
		lbNgayChuyen.setPreferredSize(lbNgayGiaoHang.getPreferredSize());
		lbNgayDatHang.setPreferredSize(lbNgayGiaoHang.getPreferredSize());
		lbNoiNhan.setPreferredSize(new Dimension((int)lbNgayGiaoHang.getPreferredSize().getWidth() + 1, (int)lbNgayGiaoHang.getPreferredSize().getHeight()));
		lbNoiNhan.setAlignmentX(LEFT_ALIGNMENT);
		
		
		// tool tip
		txtMaHD.setToolTipText("Nhập vào Mã Hóa Đơn");
		txtMaKH.setToolTipText("Nhập vào Mã Khách Hàng");
		txtMaNV.setToolTipText("Nhập vào Mã Nhân viên");
		dateNgayChuyen.setToolTipText("Nhập vào Ngày chuyển");
		dateNgayDatHang.setToolTipText("Nhập vào ngày đặt hàng");
		dateNgayGiaoHang.setToolTipText("Nhập vào ngày giao hàng");
		txtNoiNhan.setToolTipText("Nhập vào địa chỉ nhận");
	
		
		panelBody.add(panelHD_KH);
		panelBody.add(panelNV_ND);
		panelBody.add(panelNG_NC);
		panelBody.add(panelNN);
		
		JPanel panelMenuControl = new JPanel();
		panelMenuControl.add(btnThem = new JButton("Thêm"));
		panelMenuControl.add(btnXoa = new JButton("Xóa"));
		panelMenuControl.add(btnXoaTrang = new JButton("Xóa Trắng"));
		panelMenuControl.add(btnSua = new JButton("Sửa"));
		panelMenuControl.add(btnLuu = new JButton("Lưu"));
		panelMenuControl.add(btnTimKiem = new JButton("Tìm Kiếm"));
		panelMenuControl.add(btnThoat = new JButton("Thoát"));
		panelMenuControl.setMaximumSize(new Dimension(this.getWidth(), 20));
		panelBody.add(panelMenuControl);
		
		
		btnLuu.setToolTipText("Lưu Thay Đổi");
		btnSua.setToolTipText("Sửa thông tin");
		btnThem.setToolTipText("Thêm hóa đơn");
		btnThoat.setToolTipText("Thoát chương trình");
		btnXoa.setToolTipText("Xóa hóa đơn");
		btnTimKiem.setToolTipText("Tìm theo Mã Hóa đơn:");
		
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
		panelTable.setBorder(BorderFactory.createTitledBorder("Danh Sách Hóa Đơn"));
		
		String fieldName[] = {"Mã Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Đặt Hàng", "Ngày Giao Hàng", "Ngày Chuyển", "Nơi Nhận"};
		modalTableHoaDon = new DefaultTableModel(fieldName, 0);
		tableHoaDon = new JTable(modalTableHoaDon);
		JScrollPane scrollTable = new JScrollPane(tableHoaDon);

		JPanel panelTemp1 = new JPanel();
		panelTemp1.add(scrollTable);
		scrollTable.setPreferredSize(new Dimension(835, (int)scrollTable.getPreferredSize().getHeight()));
		//panelTemp1.setPreferredSize(new Dimension(1000, (int)panelTemp1.getPreferredSize().getHeight()));
		
		panelTable.add(panelTemp1);
		panelBody.add(panelTable);
		this.add(panelBody, BorderLayout.SOUTH);
		
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		tableHoaDon.addMouseListener(this);
		setWhenEditField(false);
		btnLuu.setEnabled(false);
	}
	
	
	public static void main(String[] args) {
		new FormHoaDon("Hello","Test").setVisible(true);
	}
	public String StringToDate(Date date){	
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	   }
	public void loadHoaDonToTable() {
		while (tableHoaDon.getRowCount() != 0) {
			modalTableHoaDon.removeRow(0);
		}
		ArrayList<HoaDon> dsHoaDon = hoaDon_dao.getAllHoaDon();
		for (HoaDon hd : dsHoaDon) {
			String data[] = {hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getMaNhanVien(), StringToDate(hd.getNgayDatHang()), 
					StringToDate(hd.getNgayGiaoHang()),
					StringToDate(hd.getNgayGiaoHang()), hd.getNoiNhanHang()};
			modalTableHoaDon.addRow(data);
		}
		if (tableHoaDon.getRowCount() != 0) {
			tableHoaDon.setRowSelectionInterval(0, 0);
			sendDataToTxt(0);
		}
		
	}
	public void setWhenEditField(Boolean b) {
		txtMaHD.setEditable(false);
		txtMaKH.setEditable(false);
		txtMaNV.setEditable(b);
		dateNgayChuyen.setEnabled(b);;
		dateNgayDatHang.setEnabled(b);
		dateNgayGiaoHang.setEnabled(b);
		txtNoiNhan.setEditable(b);
	}
	public void setWhenAddData(Boolean b) {
		txtMaHD.setEditable(b);
		txtMaKH.setEditable(b);
		txtMaNV.setEditable(b);
		dateNgayChuyen.setEnabled(b);;
		dateNgayDatHang.setEnabled(b);
		dateNgayGiaoHang.setEnabled(b);
		txtNoiNhan.setEditable(b);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tableHoaDon)) {
			int row = tableHoaDon.getSelectedRow();
			if (row != -1) {
				sendDataToTxt(row);
			}
		}
	}
	
	public void sendDataToTxt(int row) {
		txtMaHD.setText(tableHoaDon.getValueAt(row, 0).toString());
		txtMaKH.setText(tableHoaDon.getValueAt(row, 1).toString());
		txtMaNV.setText(tableHoaDon.getValueAt(row, 2).toString());
		try {
		    String date = tableHoaDon.getValueAt(row, 3).toString();
		    Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		    dateNgayDatHang.setDate(date2);
		} catch (Exception e2) {
		    System.out.println(e2);
		}
		try {
			 String date = tableHoaDon.getValueAt(row, 4).toString();
			 Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			 dateNgayGiaoHang.setDate(date2);
		} catch (Exception e2) {
			// TODO: handle exception
			 System.out.println(e2);
		}
		try {
			 String date = tableHoaDon.getValueAt(row, 5).toString();
			 Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			 dateNgayChuyen.setDate(date2);
		} catch (Exception e2) {
			// TODO: handle exception
			 System.out.println(e2);
		}
		txtNoiNhan.setText(tableHoaDon.getValueAt(row,  6).toString());
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
	public void xoaTrangTxtField() {
		txtMaHD.setText("");
		txtMaKH.setText("");
		txtMaNV.setText("");
		txtNoiNhan.setText("");
		dateNgayChuyen.setDate(null);
		dateNgayDatHang.setDate(null);
		dateNgayGiaoHang.setDate(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				xoaTrangTxtField();
				btnThem.setText("Hủy");
				btnSua.setEnabled(false);
				btnLuu.setEnabled(true);
				btnXoa.setEnabled(false);
				btnTimKiem.setEnabled(false);
				setWhenAddData(true);
			} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
				if (tableHoaDon.getRowCount() != 0) {
					sendDataToTxt(0);
				}
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnLuu.setEnabled(false);
				btnXoa.setEnabled(true);
				btnTimKiem.setEnabled(true);
				setWhenAddData(false);
			}
		} else if(o.equals(btnSua)) {
			if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				setWhenEditField(true);
				btnSua.setText("Hủy");
				btnThem.setEnabled(false);
				btnLuu.setEnabled(true);
				btnXoa.setEnabled(false);
				btnTimKiem.setEnabled(false);
			} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				btnSua.setText("Sửa");
				setWhenEditField(false);
				btnThem.setEnabled(true);
				btnLuu.setEnabled(false);
				btnXoa.setEnabled(true);
				btnTimKiem.setEnabled(true);
				
			}
		} else if (o.equals(btnThoat)) {
			int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát");
			if (isYes == 0) {
				System.exit(0);
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrangTxtField();
		} else if (o.equals(btnXoa)) {
			int rowSeleted = tableHoaDon.getSelectedRow();
			if (rowSeleted != -1) {
				int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hay không");
				if (isYes == 0) {
					String maHD = tableHoaDon.getValueAt(rowSeleted, 0).toString();
					boolean isDel = hoaDon_dao.deleteHoaDon(maHD);
					if (isDel) {
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
						loadHoaDonToTable();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
					
				}
			} else {
				JOptionPane.showMessageDialog(null,  "Bạn phải chọn vào dòng cần xóa","Thông Báo", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (o.equals(btnLuu)) {
			if (btnThem.getText().equalsIgnoreCase("Hủy")) {
				String maHoaDon = txtMaHD.getText();
				String maKhachHang = txtMaKH.getText().trim();
				String maNhanVien = txtMaNV.getText();
				Date ngayDatHang = dateNgayDatHang.getDate();
				Date ngayGiaoHang = dateNgayGiaoHang.getDate();
				Date ngayChuyenHang = dateNgayChuyen.getDate();
				String noiNhan = txtNoiNhan.getText();
				HoaDon hoaDon = new HoaDon(maHoaDon, maNhanVien, maKhachHang, ngayDatHang, ngayGiaoHang, ngayChuyenHang, noiNhan);
				boolean isAdded = hoaDon_dao.addHoaDon(hoaDon);
				if (!isAdded) {
					JOptionPane.showMessageDialog(null, "Thêm vào thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Thêm vào thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					loadHoaDonToTable();
				}
				btnThem.setText("Thêm");
				btnXoa.setEnabled(true);
				btnSua.setEnabled(true);
				btnTimKiem.setEnabled(true);
			} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				String maHoaDon = txtMaHD.getText();
				String maKhachHang = txtMaKH.getText().trim();
				String maNhanVien = txtMaNV.getText();
				Date ngayDatHang = dateNgayDatHang.getDate();
				Date ngayGiaoHang = dateNgayGiaoHang.getDate();
				Date ngayChuyenHang = dateNgayChuyen.getDate();
				String noiNhan = txtNoiNhan.getText();
				HoaDon hoaDon = new HoaDon(maHoaDon, maNhanVien, maKhachHang, ngayDatHang, ngayGiaoHang, ngayChuyenHang, noiNhan);
				boolean isModifield = hoaDon_dao.modifieldHoaDon(hoaDon);
				if (isModifield) {
					JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					loadHoaDonToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Sửa thất bại", "Thống báo", JOptionPane.ERROR_MESSAGE);
				}
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnTimKiem.setEnabled(true);
				setWhenEditField(false);
			} 
		} else if (o.equals(btnTimKiem)) {
			int flag = 0;
			String maHD = JOptionPane.showInputDialog(null, "Nhập vào mã hóa đơn cần tìm");
			if (maHD == null) {
				return;
			}
			for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
				if (tableHoaDon.getValueAt(i, 0).toString().equalsIgnoreCase(maHD)) {
					tableHoaDon.setRowSelectionInterval(i, i);
					sendDataToTxt(i);
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				JOptionPane.showMessageDialog(null, "Không có Mã Hóa đơn này trong danh sách");
			}
		}
		if (e.getSource().equals(this.menuLinhKien)) {
			new QuanLySanPham(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		}
		else if (e.getSource().equals(this.menuNhanVien)) {
			try {
				new QuanLyNhanVien(this.maNhanVien, this.tenNhanVien).setVisible(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
			return;
		}
		else if (e.getSource().equals(this.menuKhachHang)) {
			new QuanLyKhachHang(this.maNhanVien,this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		}
		else if (e.getSource().equals(this.menuHoaDon)) {
			new FormHoaDon(this.maNhanVien,this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		}
		else if (e.getSource().equals(this.menuQuanLyChiTietHoaDon)) {
			new FormChiTietHoaDon(maNhanVien, tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		}
		else if (e.getSource().equals(this.menuLoaiLinhKien)) {
<<<<<<< HEAD
			new QuanLyLoaiLinhKien(maNhanVien,tenNhanVien).setVisible(true);
			this.setVisible(false);
=======
			new FormChiTietHoaDon(maNhanVien,tenNhanVien).setVisible(true);
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Chua lam ");
>>>>>>> ffc7e4e30e3d6e50d37331b3107e22e0c514bce9
		}
		else if (e.getSource().equals(this.menuNhaCungCap)) {
			new QuanLyNhaCungCap(maNhanVien, tenNhanVien);
			this.dispose();
		}
	}
	@Override
	public void menuSelected(MenuEvent e) {
		if (e.getSource().equals(this.menuHome)) {
			new TrangChu(this.maNhanVien, this.tenNhanVien);
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuHeThong)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn xác nhận muốn thoát chương trình", "",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				this.setVisible(false);
				new FormLogin().setVisible(true);
				return;
			}
		} else if (e.getSource().equals(this.menuThongKe)) {
			new ThongKe(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuUser)) {
			new FormChinhSuaThongTinNhanVien(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		}
	}
	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
}
