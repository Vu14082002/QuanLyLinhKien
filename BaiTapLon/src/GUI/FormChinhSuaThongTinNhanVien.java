package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import ConnectionDB.ConnectDB;
import Entity.NhanVien;
import dao.LoaiLinhKien_DAO;
import dao.NhanVien_DAO;

public class FormChinhSuaThongTinNhanVien extends JFrame implements ActionListener, MouseListener, MenuListener {
	private JLabel labelHeader, labelMa, labelHo, labelTen, labelTel, labelSDT, labelDiaChi, labellEmail, labelEmail,
			labelGioiTinh, labelNgayVaoLam, labelSoCCCD, labelMatKhau, labelPhongBan;
	private JTextField txtMa, txtHo, txtTen, txtSDT, txtDiaChi, txtEmail, txtCCCD, txtMaPhongBan;
	private JPasswordField txtMatKhau;
	private ButtonGroup btnGroupGender;
	private JRadioButton btnNam, btnNu;
	private JDateChooser dateVao;
	private NhanVien_DAO nv_Dao;
	private JButton btnSua, btnLuu, btnThoat;
	private String tenNhanVien;
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
	private JMenuItem menuLoai;
	private JMenuItem menuHoaDon;
	private JMenuItem menuLoaiLinhKien;
	private JMenuItem menuQuanLyChiTietHoaDon;
	private JTree tree;

	public FormChinhSuaThongTinNhanVien(String maNhanVien, String tenNhanVien) {
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		createGUI();
		try {
			ConnectDB.getInstance().connect();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		nv_Dao = new NhanVien_DAO();
		init();
		loadDataToTextField(this.maNhanVien);
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

	public void createGUI() {
		this.setTitle("Quản Lý Linh Kiện");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);

		this.setResizable(false);
		JPanel panelCenter = new JPanel(new BorderLayout());
		labelHeader = new JLabel("Chỉnh Sửa Thông Tin Cá Nhân", SwingConstants.CENTER);
		labelHeader.setBorder(new EmptyBorder(20, 0, 0, 0));
		;
		labelHeader.setFont(new Font("Arial", Font.BOLD, 20));
		labelHeader.setForeground(Color.GRAY);
		panelCenter.add(labelHeader, BorderLayout.NORTH);

		this.add(panelCenter, BorderLayout.CENTER);

		JPanel panelBodyOfCenter = new JPanel();
		panelBodyOfCenter.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		panelBodyOfCenter.setLayout(new BoxLayout(panelBodyOfCenter, BoxLayout.Y_AXIS));

		panelBodyOfCenter.add(Box.createVerticalStrut(40));

		JPanel bodyLine1 = new JPanel();
		bodyLine1.setLayout(new BoxLayout(bodyLine1, BoxLayout.X_AXIS));
		bodyLine1.add(labelMa = new JLabel("Mã"));
		bodyLine1.add(txtMa = new JTextField(15));
		bodyLine1.add(Box.createHorizontalStrut(20));
		bodyLine1.add(labelMatKhau = new JLabel("Mật khẩu"));
		bodyLine1.add(txtMatKhau = new JPasswordField(15));
		bodyLine1.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelBodyOfCenter.add(bodyLine1);
		panelBodyOfCenter.add(Box.createVerticalStrut(20));

		JPanel bodyLine2 = new JPanel();
		bodyLine2.setLayout(new BoxLayout(bodyLine2, BoxLayout.X_AXIS));
		bodyLine2.add(labelHo = new JLabel("Họ"));
		bodyLine2.add(txtHo = new JTextField(15));
		bodyLine2.add(Box.createHorizontalStrut(20));
		bodyLine2.add(labelTen = new JLabel("Tên"));
		bodyLine2.add(txtTen = new JTextField(15));
		bodyLine2.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelBodyOfCenter.add(bodyLine2);
		panelBodyOfCenter.add(Box.createVerticalStrut(20));

		JPanel bodyLine3 = new JPanel();
		bodyLine3.setLayout(new BoxLayout(bodyLine3, BoxLayout.X_AXIS));

		bodyLine3.add(labelSoCCCD = new JLabel("CCCD"));
		bodyLine3.add(txtCCCD = new JTextField(15));
		bodyLine3.add(Box.createHorizontalStrut(20));
		bodyLine3.add(labelPhongBan = new JLabel("Phòng"));
		bodyLine3.add(txtMaPhongBan = new JTextField(15));
		bodyLine3.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelBodyOfCenter.add(bodyLine3);
		panelBodyOfCenter.add(Box.createVerticalStrut(20));

		JPanel bodyLine4 = new JPanel();
		bodyLine4.setLayout(new BoxLayout(bodyLine4, BoxLayout.X_AXIS));
		bodyLine4.setMaximumSize(new Dimension(this.getWidth(), 25));
		bodyLine4.add(labelSDT = new JLabel("SĐT"));
		bodyLine4.add(txtSDT = new JTextField(10));
		bodyLine4.add(Box.createHorizontalStrut(20));
		JPanel pGender = new JPanel();
		pGender.setLayout(new BoxLayout(pGender, BoxLayout.X_AXIS));
		pGender.add(labelGioiTinh = new JLabel("Giới tính"));
		pGender.add(Box.createHorizontalStrut(50));
		pGender.add(btnNam = new JRadioButton("Nam"));
		pGender.add(Box.createHorizontalStrut(10));
		pGender.add(btnNu = new JRadioButton("Nữ"));
		pGender.setPreferredSize(new Dimension(278, 25));
		bodyLine4.add(pGender);
		btnGroupGender = new ButtonGroup();
		btnGroupGender.add(btnNam);
		btnGroupGender.add(btnNu);
		btnNam.setSelected(true);
		panelBodyOfCenter.add(bodyLine4);
		panelBodyOfCenter.add(Box.createVerticalStrut(20));

		JPanel bodyLine5 = new JPanel();
		bodyLine5.setLayout(new BoxLayout(bodyLine5, BoxLayout.X_AXIS));
		bodyLine5.add(labellEmail = new JLabel("Email"));
		bodyLine5.add(txtEmail = new JTextField());
		txtEmail.setPreferredSize(new Dimension(88, 25));
		bodyLine5.add(Box.createHorizontalStrut(20));
		bodyLine5.add(labelNgayVaoLam = new JLabel("Ngày vào"));
		bodyLine5.add(dateVao = new JDateChooser());
		bodyLine5.setMaximumSize(new Dimension(this.getWidth(), 25));

		dateVao.setDateFormatString("dd-MM-yyyy");
		panelBodyOfCenter.add(bodyLine5);
		panelBodyOfCenter.add(Box.createVerticalStrut(20));

		JPanel bodyLine6 = new JPanel();

		bodyLine6.setLayout(new BoxLayout(bodyLine6, BoxLayout.X_AXIS));
		bodyLine6.add(labelDiaChi = new JLabel("Địa Chỉ"));
		bodyLine6.add(txtDiaChi = new JTextField(15));
		bodyLine6.setMaximumSize(new Dimension(this.getWidth(), 25));
		panelBodyOfCenter.add(bodyLine6);
		panelBodyOfCenter.add(Box.createVerticalStrut(20));

		panelCenter.add(panelBodyOfCenter, BorderLayout.CENTER);
		labelMa.setPreferredSize(labelMatKhau.getPreferredSize());
		labelHo.setPreferredSize(labelMatKhau.getPreferredSize());
		labelTen.setPreferredSize(labelMatKhau.getPreferredSize());
		labelSoCCCD.setPreferredSize(labelMatKhau.getPreferredSize());
		labelPhongBan.setPreferredSize(labelMatKhau.getPreferredSize());
		labelSDT.setPreferredSize(labelMatKhau.getPreferredSize());
		labelGioiTinh.setPreferredSize(labelMatKhau.getPreferredSize());
		labelDiaChi.setPreferredSize(labelMatKhau.getPreferredSize());
		labellEmail.setPreferredSize(labelMatKhau.getPreferredSize());
		// labelNgayVaoLam.setPreferredSize(labelMatKhau.getPreferredSize());

		JPanel panelEastOfCenter = new JPanel();
		panelEastOfCenter.setBorder(BorderFactory.createTitledBorder("Chức Năng"));
		panelEastOfCenter.setPreferredSize(new Dimension(200, this.getHeight()));

		JPanel panelGroupBTN = new JPanel();
		panelGroupBTN.setLayout(new BoxLayout(panelGroupBTN, BoxLayout.Y_AXIS));
		panelGroupBTN.add(Box.createVerticalStrut(150));
		panelGroupBTN.add(btnSua = new JButton("Sửa"));
		panelGroupBTN.add(Box.createVerticalStrut(30));
		panelGroupBTN.add(btnLuu = new JButton("Lưu"));
		panelGroupBTN.add(Box.createVerticalStrut(30));
		panelGroupBTN.add(btnThoat = new JButton("Thoát"));
		btnSua.setSize(300, 75);
		btnSua.setBackground(Color.red);
		btnSua.setForeground(Color.white);
		btnSua.setMaximumSize(btnSua.getSize());
		btnLuu.setSize(300, 75);
		btnLuu.setBackground(Color.gray);
		btnLuu.setForeground(Color.white);
		btnLuu.setMaximumSize(btnSua.getSize());
		btnThoat.setBackground(Color.LIGHT_GRAY);
		btnThoat.setForeground(Color.white);
		panelEastOfCenter.add(panelGroupBTN, BorderLayout.CENTER);
		panelCenter.add(panelEastOfCenter, BorderLayout.EAST);

		txtMa.setEditable(false);
		txtCCCD.setEditable(false);
		dateVao.setEnabled(false);
		txtMaPhongBan.setEditable(false);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnThoat.addActionListener(this);
		btnLuu.setEnabled(false);
		setEditedTextField(false);
	}

	public void setEditedTextField(Boolean b) {
		txtHo.setEditable(b);
		txtTen.setEditable(b);
		txtMatKhau.setEditable(b);
		txtSDT.setEditable(b);
		txtEmail.setEditable(b);
		txtDiaChi.setEditable(b);

	}

	public static void main(String[] args) {
		new FormChinhSuaThongTinNhanVien("Hello", "Nhom 8").setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	public String StringToDate(Date date) {

		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}

	public void loadDataToTextField(String maNV) {
		NhanVien nv = nv_Dao.getNhanVien(maNV);
		txtMa.setText(maNV);
		txtMatKhau.setText(nv.getMatKhau()+"");
		txtHo.setText(nv.getHo());
		txtTen.setText(nv.getTen());
		txtCCCD.setText(nv.getSoCCCD());
		txtMaPhongBan.setText(nv.getMaPhongBan());
		txtSDT.setText(nv.getSdt());
		if (nv.isGioiTinh()) {
			btnNam.setSelected(true);
		} else {
			btnNu.setSelected(true);
		}
		txtEmail.setText(nv.getEmail());
		txtDiaChi.setText(nv.getDiaChi());
		dateVao.setDate(nv.getNgayVaoLam());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnSua)) {
			if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				btnSua.setText("Hủy");
				btnLuu.setBackground(Color.GREEN);
				btnLuu.setEnabled(true);
				setEditedTextField(true);
			} else {
				btnSua.setText("Sửa");
				btnLuu.setEnabled(false);
				btnLuu.setBackground(Color.gray);
				setEditedTextField(false);
			}
		} else if (o.equals(btnLuu)) {
			if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn lưu?");
				if (isYes == 0) {
					String maNV = txtMa.getText();
					String matKhau = String.valueOf(txtMatKhau.getPassword());
					String ho = txtHo.getText();
					String ten = txtTen.getText();
					String CCCD = txtCCCD.getText();
					String maPhongBan = txtMaPhongBan.getText();
					String soDienThoai = txtSDT.getText();
					boolean gender = (btnNam.isSelected()) ? true : false;
					String email = txtEmail.getText();
					Date dateNgayVao = dateVao.getDate();
					String diaChi = txtDiaChi.getText();
					NhanVien nv = new NhanVien(maNV, ho, ten, soDienThoai, diaChi, email, gender, dateNgayVao, CCCD,
							matKhau, maPhongBan);
//					boolean isMod = nv_Dao.capNhatNhanVien(nv);
					try {
						if (nv_Dao.capNhatNhanVien(nv)) {
							JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							JOptionPane.showMessageDialog(null, "Cập nhật thất bại", "Thông báo",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setEditedTextField(false);
					btnSua.setText("Sửa");
					btnLuu.setBackground(Color.gray);
					loadDataToTextField(maNV);
				}
			}
		} else if (o.equals(btnThoat)) {
			System.exit(0);
		}
		if (e.getSource().equals(this.menuLinhKien)) {
			new QuanLySanPham(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuNhanVien)) {
			try {
				new QuanLyNhanVien(this.maNhanVien, this.tenNhanVien).setVisible(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuKhachHang)) {
			new QuanLyKhachHang(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuHoaDon)) {
			new FormHoaDon(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuQuanLyChiTietHoaDon)) {
			new FormChiTietHoaDon(maNhanVien, tenNhanVien).setVisible(true);
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuLoaiLinhKien)) {
			new QuanLyLoaiLinhKien(maNhanVien, tenNhanVien).setVisible(true);
			this.setVisible(false);
		} else if (e.getSource().equals(this.menuNhaCungCap)) {
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
