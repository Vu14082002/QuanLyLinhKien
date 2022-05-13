package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import ConnectionDB.ConnectDB;
import dao.ChiTietHoaDon_Dao;
import dao.LinhKien_DAO;
import dao.LoaiLinhKien_DAO;
import Entity.LinhKien;
import Entity.LoaiLinhKien;

public class QuanLyLoaiLinhKien extends JFrame implements ActionListener, MouseListener, MenuListener {

	private static final long serialVersionUID = 1L;
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
	private JMenuItem menuQuanLyChiTietHoaDon;
	private JTree tree;
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

	public QuanLyLoaiLinhKien(String maNhanVien, String tenNhanVien) {
		try {
			ConnectDB.getInstance().connect();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
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
		pnNorth.add(pnNorth1, BorderLayout.NORTH);

		JPanel pnCenter1 = new JPanel();
		Box b, b1;
		b = Box.createVerticalBox();
		b.setPreferredSize(new Dimension(840, 70));

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
		pnSouth1.setPreferredSize(new Dimension(1030, 70));
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
		pnNorth.add(pnCenter1, BorderLayout.CENTER);
		pnNorth.add(pnSouth1, BorderLayout.SOUTH);

		TitledBorder tileDanhSach = new TitledBorder("Danh sách loại linh kiện");
		JPanel pnCenter = new JPanel();
		pnCenter.setBorder(tileDanhSach);
		dao.LoaiLinhKien_DAO llkDao = new dao.LoaiLinhKien_DAO();
		String[] headers = { "Mã Loại Linh Kiện", "Tên Loại Linh Kiện" };
		model = new DefaultTableModel(headers, 0);
		table = new JTable(model);

		JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setPreferredSize(new Dimension(930, 230));
		pnCenter.add(sc);

		this.add(pnNorth, BorderLayout.NORTH);
		this.add(pnCenter, BorderLayout.CENTER);

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

	public static void main(String[] args) {
		new QuanLyLoaiLinhKien("Nguyen Van", "Tesst").setVisible(true);
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
		txtMa.setEditable(true);
		txtTen.setEditable(true);
	}

	public void loadLLKToTable() {
		while (table.getRowCount() != 0) {
			model.removeRow(0);
		}
		ArrayList<LoaiLinhKien> dsLLK = (ArrayList<LoaiLinhKien>) llk_dao.getAllLoaiLinhKien();
		for (LoaiLinhKien llk : dsLLK) {
			String data[] = { llk.getMaloai(), llk.getTenLinhKien() };
			model.addRow(data);
		}
		if (table.getRowCount() != 0) {
			table.setRowSelectionInterval(0, 0);
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
		} else if (o.equals(bttSua)) {
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
			new TrangChu(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.dispose();
			ConnectDB.getInstance().connect();
			;
		} else if (o.equals(bttXoaTrang)) {
			txtMa.setText("");
			txtTen.setText("");
		} else if (o.equals(bttXoa)) {
			LinhKien_DAO lkDao = new LinhKien_DAO();
			if (this.table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Bạn cần chọn dòng muốn xoá");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "Bạn xác nhận xoá dòng đẫ chọn, và có thể làm mất mát dữ liệu",
					"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String maLLK = this.model.getValueAt(table.getSelectedRow(), 0).toString();
				LoaiLinhKien_DAO lllk_dao = new LoaiLinhKien_DAO();
				ChiTietHoaDon_Dao cthd = new ChiTietHoaDon_Dao();
				List<LinhKien> lkTheoMaLLK = lkDao.getAllLinhKienMALLK(maLLK);
				for (LinhKien linhKien : lkTheoMaLLK) {
					cthd.xoaLk(linhKien.getMaLinhKien());
				}
				lkDao.xoaLinhKienTheoMaLoaiLinhKien(maLLK);
				if (llk_dao.deleteLLK(maLLK)) {
					loadLLKToTable();
					JOptionPane.showMessageDialog(this, "Xoá thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Xoá không thành công");
				}
			}
		} else if (o.equals(bttLuu)) {
			if (bttThem.getText().equalsIgnoreCase("Hủy")) {
				String ma = txtMa.getText();
				String ten = txtTen.getText();
				LoaiLinhKien llk = new LoaiLinhKien(ma, ten);
				boolean isAdded = llk_dao.addLoaiLinhKien(llk);
				if (!isAdded) {
					JOptionPane.showMessageDialog(null, "Thêm vào thất bại", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Thêm vào thành công", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
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
		} else if (e.getSource().equals(this.menuLinhKien)) {
			new QuanLySanPham(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
		} else if (e.getSource().equals(this.menuNhanVien)) {
			try {
				new QuanLyNhanVien(this.maNhanVien, this.tenNhanVien).setVisible(true);
				this.setVisible(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
		} else if (e.getSource().equals(this.menuKhachHang)) {
			new QuanLyKhachHang(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
		} else if (e.getSource().equals(this.menuHoaDon)) {
			new FormHoaDon(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
		} else if (e.getSource().equals(this.menuQuanLyChiTietHoaDon)) {
			new FormChiTietHoaDon(maNhanVien, tenNhanVien).setVisible(true);
			this.setVisible(false);
		} else if (e.getSource().equals(this.menuLoaiLinhKien)) {
			new QuanLyLoaiLinhKien(maNhanVien, tenNhanVien).setVisible(true);
			this.setVisible(false);
		} else if (e.getSource().equals(this.menuNhaCungCap)) {
			new QuanLyNhaCungCap(maNhanVien, tenNhanVien);
			this.setVisible(false);
		}

	}

	@Override
	public void menuSelected(MenuEvent e) {
		if (e.getSource().equals(this.menuHome)) {
			this.setVisible(false);
			new TrangChu(this.maNhanVien, this.tenNhanVien);
			
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
