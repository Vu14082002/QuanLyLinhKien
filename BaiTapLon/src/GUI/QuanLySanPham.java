package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import javax.xml.validation.Validator;

import ConnectionDB.ConnectDB;
import Entity.LinhKien;
import Entity.LoaiLinhKien;
import Entity.NhaCungCap;
import dao.LinhKien_DAO;
import dao.LoaiLinhKien_DAO;
import dao.NhaCungCap_DAO;

public class QuanLySanPham extends JFrame implements ActionListener, MouseListener,MenuListener {
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
	// dTren la de Menu
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnTim;
	private JTextField txtMa;
	private JTextField txtSoLuong;
	private JTextField txtDiaChi;
	private JTextField txtMaNhaCCC;
	private JTextField txtmaLoai;
	private JTextField txtDonGia;
	private JTextField txtTen;
	private LinhKien_DAO linhKien_DAO;
	private LinhKien linhKien;
	private DefaultTableModel model;
	private JTable table;
	private JLabel lbAnh;
	public QuanLySanPham(String maNhanVien, String tenNhanVien) {
		ConnectDB.getInstance().connect();
		this.setTitle("Quản lý linh kiện");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		this.setCursor(Cursor.HAND_CURSOR);
		homeCenter();
		this.linhKien_DAO = new LinhKien_DAO();
		this.linhKien = new LinhKien();
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
	public void homeCenter() {
		JPanel pnMain = new JPanel();
		TitledBorder tieuDe = new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Sản phẩm");
		tieuDe.setTitleFont(new Font("arial", Font.BOLD, 14));
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(pnMain);
		scroll.setBorder(tieuDe);
		this.add(scroll, BorderLayout.CENTER);
		JLabel lbma = new JLabel("Mã Linh Kiện:");
		JLabel lbTen = new JLabel("Tên Linh Kiện:");
		JLabel lbSoLuong = new JLabel("Số lượng:");
		JLabel lbDiaChi = new JLabel("Địa chỉ hình ảnh:");
		JLabel lbMaNhaCC = new JLabel("Mã nhà cung cấp:");
		JLabel lbLoai = new JLabel("Mã loại:");
		JLabel lbdonGia = new JLabel("Đơn giá:");
		txtMa = new JTextField(12);
		txtTen = new JTextField(12);
		txtSoLuong = new JTextField(12);
		txtDiaChi = new JTextField(12);
		txtMaNhaCCC = new JTextField(12);
		txtmaLoai = new JTextField(12);
		txtDonGia = new JTextField(12);
		// pn top
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.X_AXIS));
		pnTop.setMaximumSize(new Dimension(1000, 270));
		pnTop.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
//		JPanel
		JPanel pnCol1 = new JPanel();
		pnCol1.setMaximumSize(new Dimension(250, 270));
		lbAnh = new JLabel("noi nay de anh");
		lbAnh.setMinimumSize(new Dimension(250, 170));
		lbAnh.setBackground(Color.BLUE);
		JPanel pnANh = new JPanel();
		pnANh.setLayout(new GridLayout(1, 1));
		pnANh.add(lbAnh);
		pnANh.setPreferredSize(new Dimension(230, 250));
		pnCol1.add(pnANh);
		pnCol1.setBorder(BorderFactory.createLineBorder(Color.red));
		lbAnh.setPreferredSize(new Dimension(100, 100));
		// pncol 2
		JPanel pnCol2 = new JPanel();
		pnCol2.setLayout(new BoxLayout(pnCol2, BoxLayout.Y_AXIS));
		pnCol2.setMaximumSize(new Dimension(500, 270));
		JPanel pnCol2_1 = new JPanel();
		pnCol2_1.setLayout(new BoxLayout(pnCol2_1, BoxLayout.X_AXIS));
		pnCol2_1.add(lbma);
		lbma.setPreferredSize(new Dimension(100, 25));
		pnCol2_1.add(Box.createRigidArea(new Dimension(48, 0)));
		pnCol2_1.add(txtMa);
		txtMa.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_1);

		JPanel pnCol2_2 = new JPanel();
		pnCol2_2.setLayout(new BoxLayout(pnCol2_2, BoxLayout.X_AXIS));
		pnCol2_2.add(lbTen);
		lbTen.setPreferredSize(new Dimension(100, 25));
		pnCol2_2.add(Box.createRigidArea(new Dimension(43, 0)));
		pnCol2_2.add(txtTen);
		txtTen.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_2);

		JPanel pnCol2_3 = new JPanel();
		pnCol2_3.setLayout(new BoxLayout(pnCol2_3, BoxLayout.X_AXIS));
		pnCol2_3.add(lbMaNhaCC);
		lbMaNhaCC.setPreferredSize(new Dimension(100, 25));
		pnCol2_3.add(Box.createRigidArea(new Dimension(25, 0)));
		pnCol2_3.add(txtMaNhaCCC);
		txtMaNhaCCC.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_3);

		JPanel pnCol2_4 = new JPanel();
		pnCol2_4.setLayout(new BoxLayout(pnCol2_4, BoxLayout.X_AXIS));
		pnCol2_4.add(lbLoai);
		lbLoai.setPreferredSize(new Dimension(100, 25));
		pnCol2_4.add(Box.createRigidArea(new Dimension(72, 0)));
		pnCol2_4.add(txtmaLoai);
		txtmaLoai.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_4);

		JPanel pnCol2_5 = new JPanel();
		pnCol2_5.setLayout(new BoxLayout(pnCol2_5, BoxLayout.X_AXIS));
		pnCol2_5.add(lbSoLuong);
		lbSoLuong.setPreferredSize(new Dimension(100, 25));
		pnCol2_5.add(Box.createRigidArea(new Dimension(63, 0)));
		pnCol2_5.add(txtSoLuong);
		txtSoLuong.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_5);

		JPanel pnCol2_6 = new JPanel();
		pnCol2_6.setLayout(new BoxLayout(pnCol2_6, BoxLayout.X_AXIS));
		pnCol2_6.add(lbdonGia);
		lbdonGia.setPreferredSize(new Dimension(100, 25));
		pnCol2_6.add(Box.createRigidArea(new Dimension(70, 0)));
		pnCol2_6.add(txtDonGia);
		txtDonGia.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_6);

		JPanel pnCol2_7 = new JPanel();
		pnCol2_7.setLayout(new BoxLayout(pnCol2_7, BoxLayout.X_AXIS));
		pnCol2_7.add(lbDiaChi);
		lbAnh.setPreferredSize(new Dimension(100, 25));
		pnCol2_7.add(Box.createRigidArea(new Dimension(31, 0)));
		pnCol2_7.add(txtDiaChi);
		txtDiaChi.addMouseListener(this);
		txtDiaChi.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_7);
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.setBorder(BorderFactory.createLineBorder(Color.red));

		// pn col 3
		JPanel pnCol3 = new JPanel();
		pnCol3.setLayout(new BoxLayout(pnCol3, BoxLayout.Y_AXIS));
		pnCol3.setMaximumSize(new Dimension(260, 270));
		pnCol3.setBorder(BorderFactory.createLineBorder(Color.cyan));
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xoá");
		btnCapNhat = new JButton("Cập nhật");
		btnTim = new JButton("Tìm");
		btnThem.setMaximumSize(new Dimension(100, 30));
		btnXoa.setMaximumSize(new Dimension(100, 30));
		btnCapNhat.setMaximumSize(new Dimension(100, 30));
		btnTim.setMaximumSize(new Dimension(100, 30));
		btnXoa.setEnabled(false);
		btnCapNhat.setEnabled(false);
		JPanel pbBtn1 = new JPanel();
		pbBtn1.setLayout(new BoxLayout(pbBtn1, BoxLayout.X_AXIS));
		pbBtn1.add(Box.createRigidArea(new Dimension(35, 25)));
		pbBtn1.add(btnThem);
		pnCol3.add(Box.createRigidArea(new Dimension(0, 20)));
		pnCol3.add(pbBtn1);

		JPanel pbBtn2 = new JPanel();
		pbBtn2.setLayout(new BoxLayout(pbBtn2, BoxLayout.X_AXIS));
		pbBtn2.add(Box.createRigidArea(new Dimension(35, 25)));
		pbBtn2.add(btnXoa);
		pnCol3.add(Box.createRigidArea(new Dimension(0, 20)));
		pnCol3.add(pbBtn2);

		JPanel pbBtn3 = new JPanel();
		pbBtn3.setLayout(new BoxLayout(pbBtn3, BoxLayout.X_AXIS));
		pbBtn3.add(Box.createRigidArea(new Dimension(35, 25)));
		pbBtn3.add(btnCapNhat);
		pnCol3.add(Box.createRigidArea(new Dimension(0, 20)));
		pnCol3.add(pbBtn3);

		JPanel pbBtn4 = new JPanel();
		pbBtn4.setLayout(new BoxLayout(pbBtn4, BoxLayout.X_AXIS));
		pbBtn4.add(Box.createRigidArea(new Dimension(35, 25)));
		pbBtn4.add(btnTim);
		pnCol3.add(Box.createRigidArea(new Dimension(0, 20)));
		pnCol3.add(pbBtn4);
		// add pn
		pnTop.add(pnCol1);
		pnTop.add(pnCol2);
		pnTop.add(pnCol3);
		pnMain.add(pnTop);
		/// table
		String[] s = { "Mã linh kiện", "Tên", "mã nhà cung cấp", "Mã loại", "Số lượng", "Đơn giá nhập",
				" Địa chỉ hình ảnh" };
		model = new DefaultTableModel(s, 0);
		table = new JTable(model);

		this.table.addMouseListener(this);

		JScrollPane scrolTable = new JScrollPane(table);
		pnMain.add(scrolTable);
		this.btnCapNhat.addActionListener(this);
		this.btnThem.addActionListener(this);
		this.btnXoa.addActionListener(this);
		this.btnTim.addActionListener(this);
		loadData();
	}
	/*public boolean validator() {
		String maLinhKien = txtMa.getText().trim();
		String tenLinhKien = txtTen.getText().trim();
		String maNCC = txtMaNhaCCC.getText().trim();
		String maLoai = txtmaLoai.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String donGia = txtDonGia.getText().trim();
		String diaChiHinhAnh = txtDiaChi.getText().trim();
		int soLuongInt;
		double donGiaDou;
		
		if (maLinhKien.isEmpty() || tenLinhKien.isEmpty() || maNCC.isEmpty() || maLoai.isEmpty() || soLuong.isEmpty() || donGia.isEmpty()
				|| diaChiHinhAnh.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không được để trống field nào!", "Cảnh báo", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
			
			try {
				soLuongInt = Integer.parseInt(soLuong);
				if (soLuongInt <= 0) {
					JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Đầu vào của field Số lượng không hợp lệ!");
				return false;
			}
			try {
				donGiaDou = Double.parseDouble(donGia);
				if (donGiaDou < 0) {
					JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn bằng 0");
					return false;
				}
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Đầu vào của field Đơn giá không hợp lệ!");
				return false;
			}
		
		if (!maLinhKien.matches("^LK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã Linh kiện theo định dạng: LKxxx với x là các số bất kỳ");
			return false;
		}
		if (!tenLinhKien.matches("^([A-Z][a-z]+)(\\s[a-zA-Z0-9]+)*$")) {
			JOptionPane.showMessageDialog(null, "Tên linh kiện bắt đầu bằng in hoa, từ đầu tiên không có số. các từ không có kí tự đặc biệt");
			return false;
		}
		if (!maNCC.matches("^NCC\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã Nhà cung cấp theo định dạng: NCCxxx với x là các số bất kỳ");
			return false;
		}
		if (!maLoai.matches("^LLK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã Loại linh kiện theo định dạng: LLKxxx với x là các số bất kỳ");
			return false;
		}
		
		if (!diaChiHinhAnh.matches("(.png|.jpg)$")) {
			JOptionPane.showMessageDialog(null, "Đuôi định dạng phải là .png hoặc .jpg");
			return false;
		}
		return true;
	}*/
	public void timTheoMaLoai() {
		
		String maMaLoai = JOptionPane.showInputDialog("Nhập vào mã cần tìm");
		ArrayList<LinhKien> dslk = (ArrayList<LinhKien>) linhKien_DAO.getLinhKienTheoMa(maMaLoai);
		if (dslk.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không có mã linh kiện này trong bảng");
			
		} else {
			while (this.model.getRowCount() > 0) {
				this.model.removeRow(0);
			}
			
			for (LinhKien lk : dslk) {
				model.addRow(new Object[] { lk.getMaLinhKien(), lk.getTenLinhKien(), lk.getMaNhaCungCap(), lk.getMaLoai(),
						lk.getSoLuong(), lk.getDonGia(), lk.getDiaChiHinhAnh() });
			}
			txtMa.setText(dslk.get(0).getMaLinhKien());
			txtmaLoai.setText(dslk.get(0).getMaLoai());
			txtDiaChi.setText(dslk.get(0).getDiaChiHinhAnh());
			txtDonGia.setText(dslk.get(0).getDonGia()+"");
			txtMaNhaCCC.setText(dslk.get(0).getMaNhaCungCap());
			txtSoLuong.setText(dslk.get(0).getSoLuong()+"");
			txtTen.setText(dslk.get(0).getTenLinhKien());
			table.setRowSelectionInterval(0, 0);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String tenSuKien = e.getActionCommand();
		if (tenSuKien.equalsIgnoreCase("Thêm")) {
			ktInput();
		}
		if (tenSuKien.equalsIgnoreCase("Huỷ")) {
		
			them();
			this.btnThem.setText("Thêm");
		}
		if (o.equals(btnTim)) {
			if (btnTim.getText().equalsIgnoreCase("Tìm")) {
				btnTim.setText("Hủy");
				timTheoMaLoai();
			} else {
				btnTim.setText("Tìm");
				loadData();
				
			}
		}
		if (tenSuKien.equalsIgnoreCase("Xoá")) {
			int row = this.table.getSelectedRow();
			if (this.table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Bạn phải chọn dòng muốn xoá");
			} else {
				if (JOptionPane.showConfirmDialog(this, "Bạn xác nhận xoá dòng đã chọn", "Xác nhân",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maSanPham = this.model.getValueAt(row, 0).toString();
					System.out.println(maSanPham);
					this.linhKien_DAO = new LinhKien_DAO();
					try {
						if (this.linhKien_DAO.xoaLinhKien(maSanPham)) {
							JOptionPane.showMessageDialog(this, "Xoá thành công");
							loadData();
						} else {
							JOptionPane.showMessageDialog(this, "Xoá bị lôi", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		}
		if (tenSuKien.equalsIgnoreCase("Cập nhật")) {
			capNhat();
			this.txtMa.setEditable(false);
			this.btnCapNhat.setText("Lưu");
			this.btnCapNhat.setEnabled(true);
			this.btnThem.setEnabled(true);
			this.btnTim.setEnabled(false);
			this.btnThem.setText("Huỷ");
		}
		if (tenSuKien.equalsIgnoreCase("Lưu")) {
			ktUpdate();
			this.btnCapNhat.setText("Cập nhật");
			huy();
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
			new FormChiTietHoaDon(maNhanVien,tenNhanVien).setVisible(true);
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Chua lam ");
		}
		else if (e.getSource().equals(this.menuNhaCungCap)) {
			new QuanLyNhaCungCap(maNhanVien, tenNhanVien);
			this.dispose();
		}

	}

	public void loadData() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.linhKien_DAO = new LinhKien_DAO();
		int num = 0;
		List<LinhKien> dslk = this.linhKien_DAO.getAllLinhKien();
		for (LinhKien lk : dslk) {
			model.addRow(new Object[] { lk.getMaLinhKien(), lk.getTenLinhKien(), lk.getMaNhaCungCap(), lk.getMaLoai(),
					lk.getSoLuong(), lk.getDonGia(), lk.getDiaChiHinhAnh() });
			if (num == 0) {
				txtMa.setText(dslk.get(0).getMaLinhKien());
				txtmaLoai.setText(dslk.get(0).getMaLoai());
				txtDiaChi.setText(dslk.get(0).getDiaChiHinhAnh());
				txtDonGia.setText(dslk.get(0).getDonGia()+"");
				txtMaNhaCCC.setText(dslk.get(0).getMaNhaCungCap());
				txtSoLuong.setText(dslk.get(0).getSoLuong()+"");
				txtTen.setText(dslk.get(0).getTenLinhKien());
				table.setRowSelectionInterval(0, 0);
			}
			num++;
		}

	}

	public void huy() {
		this.txtMa.setEditable(false);
		this.txtDiaChi.setEditable(false);
		this.txtTen.setEditable(false);
		this.txtDonGia.setEditable(false);
		this.txtmaLoai.setEditable(false);
		this.txtMaNhaCCC.setEditable(false);
		this.txtSoLuong.setEditable(false);
		this.btnThem.setText("Huỷ");
		this.btnCapNhat.setEnabled(true);
		this.btnCapNhat.setText("Cập nhật");
		this.btnTim.setEnabled(false);
	}
	public void capNhat() {
		this.txtMa.setEditable(false);
		this.txtDiaChi.setEditable(true);
		this.txtTen.setEditable(true);
		this.txtDonGia.setEditable(true);
		this.txtmaLoai.setEditable(true);
		this.txtMaNhaCCC.setEditable(true);
		this.txtSoLuong.setEditable(true);
		this.btnThem.setText("Huỷ");
		this.btnCapNhat.setEnabled(true);
		this.btnTim.setEnabled(false);
	}
	public void them() {
		this.btnThem.setText("Thêm");
		this.txtMa.setText("");
		this.txtDiaChi.setText("");
		this.txtTen.setText("");
		this.txtDonGia.setText("");
		this.txtmaLoai.setText("");
		this.txtMaNhaCCC.setText("");
		this.txtSoLuong.setText("");
		this.lbAnh.setIcon(null);
		this.txtMa.setEditable(true);
		this.txtDiaChi.setEditable(true);
		this.txtTen.setEditable(true);
		this.txtDonGia.setEditable(true);
		this.txtmaLoai.setEditable(true);
		this.txtMaNhaCCC.setEditable(true);
		this.txtSoLuong.setEditable(true);
		this.btnThem.setText("Huỷ");
		this.btnCapNhat.setEnabled(false);
		this.btnTim.setEnabled(true);
	}

	public boolean ktInput() {
		String maLinhKien = this.txtMa.getText().trim();
		String tenLinhKien = this.txtTen.getText().trim();
		String nhaCungCap = this.txtMaNhaCCC.getText().trim();
		String maLoai = this.txtmaLoai.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String donGia = txtDonGia.getText().trim();
		String diaChiHinhAnh = txtDiaChi.getText().trim();
		double donGiaDou = 0;
		int soLuongInt = 0;
		if (maLinhKien.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống");
			this.txtMa.setRequestFocusEnabled(true);
			return false;
		}
		if (tenLinhKien.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên linh kiện không  được đê trống");
			this.txtTen.setRequestFocusEnabled(true);
			return false;
		}
		if (nhaCungCap.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không đc để trống");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		
	
	
		if (!maLinhKien.matches("^LK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã Linh kiện theo định dạng: LKxxx với x là các số bất kỳ");
			return false;
		}
		if (!tenLinhKien.matches("^([A-Z][a-z]+)(\\s[a-zA-Z0-9]+)*$")) {
			JOptionPane.showMessageDialog(null, "Tên linh kiện bắt đầu bằng in hoa, từ đầu tiên không có số. các từ không có kí tự đặc biệt");
			return false;
		}
		if (!nhaCungCap.matches("^NCC\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã Nhà cung cấp theo định dạng: NCCxxx với x là các số bất kỳ");
			return false;
		}
		if (!maLoai.matches("^LLK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã Loại linh kiện theo định dạng: LLKxxx với x là các số bất kỳ");
			return false;
		}
		try {
			soLuongInt = Integer.parseInt(soLuong);
			if (soLuongInt <= 0) {
				JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Đầu vào của field Số lượng không hợp lệ!");
			return false;
		}
		try {
			donGiaDou = Double.parseDouble(donGia);
			if (donGiaDou < 0) {
				JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn bằng 0");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Đầu vào của field Đơn giá không hợp lệ!");
			return false;
		}
	
		if (!diaChiHinhAnh.matches(".+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$")) {
			JOptionPane.showMessageDialog(null, "Đuôi định dạng phải là .png hoặc .jpg");
			return false;
		}
		
		NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
		List<NhaCungCap> dsNCC = nccDao.getAllNhaCungCap();
		boolean kt = false;
		for (NhaCungCap nhaCungCap2 : dsNCC) {
			if (nhaCungCap.equals(nhaCungCap2.getMaNhaCungCap())) {
				kt = true;
			}
		}
		if (!kt) {
			JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại trong Database");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		if (maLoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Loại sản phẩm không đc để trống");
			this.txtmaLoai.setRequestFocusEnabled(true);
			return false;
		}

		LoaiLinhKien_DAO loaiLK_deo = new LoaiLinhKien_DAO();
		List<LoaiLinhKien> dsLk = loaiLK_deo.getAllLoaiLinhKien();
		boolean kt2 = false;
		for (LoaiLinhKien loailk : dsLk) {
			if (maLoai.equals(loailk.getMaloai())) {
				kt2 = true;
			}
		}
		if (!kt2) {
			JOptionPane.showMessageDialog(this, "Mã loại không tồn tại");
			return false;
		}

		try {
			soLuongInt = Integer.parseInt(this.txtSoLuong.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Số lượng linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		try {
			donGiaDou = Double.parseDouble(this.txtDonGia.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Đơn giá  linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		String diaChi = this.txtDiaChi.getText().trim();
		if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn ảnh để lưu");
			return false;
		}
		String diaChiNew = "/images_LinhKien/" + diaChi;
		LinhKien_DAO lk_Dao = new LinhKien_DAO();
		LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuongInt, diaChiNew, maLoai, nhaCungCap, donGiaDou);
		if (!lk_Dao.themLinhKien(lk)) {
			JOptionPane.showMessageDialog(this, "Thêm không thành công");
			return false;
		}
		JOptionPane.showMessageDialog(this, "Thêm thành công");
		loadData();
		return true;
	}

	public boolean ktUpdate() {
		String maLinhKien = this.txtMa.getText().trim();
		String tenLinhKien = this.txtTen.getText().trim();
		String nhaCungCap = this.txtMaNhaCCC.getText().trim();
		String maLoai = this.txtmaLoai.getText().trim();
		double donGia = 0;
		int soLuong = 0;
		if (maLinhKien.equals("")) {
			JOptionPane.showMessageDialog(this, "Ma san pham ko dc de trong");
			this.txtMa.setRequestFocusEnabled(true);
			return false;
		}
		if (tenLinhKien.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên linh kiện không  được đê trống");
			this.txtTen.setRequestFocusEnabled(true);
			return false;
		}
		if (nhaCungCap.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không đc để trống");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
		List<NhaCungCap> dsNCC = nccDao.getAllNhaCungCap();
		boolean kt = false;
		for (NhaCungCap nhaCungCap2 : dsNCC) {
			if (nhaCungCap.equals(nhaCungCap2.getMaNhaCungCap())) {
				kt = true;
			}
		}
		if (!kt) {
			JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại trong Database");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		if (maLoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Loại sản phẩm không đc để trống");
			this.txtmaLoai.setRequestFocusEnabled(true);
			return false;
		}

		LoaiLinhKien_DAO loaiLK_deo = new LoaiLinhKien_DAO();
		List<LoaiLinhKien> dsLk = loaiLK_deo.getAllLoaiLinhKien();
		boolean kt2 = false;
		for (LoaiLinhKien loailk : dsLk) {
			if (maLoai.equals(loailk.getMaloai())) {
				kt2 = true;
			}
		}
		if (!kt2) {
			JOptionPane.showMessageDialog(this, "Mã loại không tồn tại");
			return false;
		}

		try {
			soLuong = Integer.parseInt(this.txtSoLuong.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Số lượng linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		try {
			donGia = Double.parseDouble(this.txtDonGia.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Đơn giá  linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		String diaChi = this.txtDiaChi.getText().trim();
		if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn ảnh để lưu");
			return false;
		}
		LinhKien_DAO lk_Dao = new LinhKien_DAO();
		LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuong, diaChi, maLoai, nhaCungCap, donGia);
		try {
			if (!lk_Dao.capNhatLinhKien(lk)) {
				JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
				return false;
			}
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadData();
		JOptionPane.showMessageDialog(this, "Cập nhật  thành công");
		return true;
	}
	public static void main(String[] args) {
		new QuanLySanPham("Hello","Test").setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(this.txtDiaChi)) {
			JFileChooser filechoose = new JFileChooser();
			int rp = filechoose.showSaveDialog(null);
			if (rp == JFileChooser.APPROVE_OPTION) {
				File file = filechoose.getSelectedFile();
				txtDiaChi.setText(file.getName());
			}
		}
		if (o.equals(this.table)) {
			int row = this.table.getSelectedRow();
			txtMa.setText(model.getValueAt(row, 0).toString());
			txtTen.setText(model.getValueAt(row, 1).toString());
			txtMaNhaCCC.setText(model.getValueAt(row, 2).toString());
			txtmaLoai.setText(model.getValueAt(row, 3).toString());
			txtSoLuong.setText(model.getValueAt(row, 4).toString());
			txtDonGia.setText(model.getValueAt(row, 5).toString());
			txtDiaChi.setText(model.getValueAt(row, 6).toString());
			String diaChi = model.getValueAt(row, 6).toString();
			ImageIcon icon = new ImageIcon(this.getClass().getResource(diaChi));
			Image img = icon.getImage();
			Image scale = img.getScaledInstance(250, 270, Image.SCALE_SMOOTH);
			ImageIcon scaleIcon = new ImageIcon(scale);
			this.lbAnh.setIcon(scaleIcon);
			huy();

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
	public void menuSelected(MenuEvent e) {
		if (e.getSource().equals(this.menuHome)) {
			new TrangChu(this.maNhanVien, this.tenNhanVien);
			this.dispose();
			return;
		} else if (e.getSource().equals(this.menuHeThong)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn xác nhận muốn thoát chương trình", "",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				this.dispose();
				new FormLogin().setVisible(true);
				return;
			}else {
				new QuanLySanPham(this.maNhanVien, this.tenNhanVien).setVisible(true);
				this.dispose();
			}
		} else if (e.getSource().equals(this.menuThongKe)) {
			new ThongKe(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.dispose();
			return;
		} else if (e.getSource().equals(this.menuUser)) {
			new FormChinhSuaThongTinNhanVien(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.dispose();
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
