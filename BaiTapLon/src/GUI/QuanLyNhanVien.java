package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.toedter.calendar.JDateChooser;

import ConnectionDB.ConnectDB;
import Entity.LinhKien;
import Entity.NhanVien;
import Entity.PhongBan;
import dao.LinhKien_DAO;
import dao.LoaiLinhKien_DAO;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;

public class QuanLyNhanVien extends JFrame implements ActionListener, MouseListener,MenuListener {
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

	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private DefaultTableModel model;
	NhanVien_DAO nhanVienDao;
	private JTable table;
	private JTextField txtMaNhanVien;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtSdt;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtSoCCCD;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private JDateChooser txtDate;
	private JPasswordField txtMatKhau;
	private JComboBox<String> cbPhongBan;
	private JPasswordField txtNhapLai;
	private JButton btnTim;

	public QuanLyNhanVien(String maNhanVien, String tenNhanVien) throws SQLException {
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		ConnectDB.getInstance().connect();
		this.setTitle("Qu???n l?? linh ki???n");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setCursor(Cursor.HAND_CURSOR);
		init();
		CenterNhanVien();
		nhanVienDao = new NhanVien_DAO();

	}

	public void init() {
		this.loaiLinhKien = new LoaiLinhKien_DAO();
		menuBar = new JMenuBar();
		menuHome = new JMenu("<html><p style='text-align:center; width:75px'>Trang ch???</p></html>");
		menuHeThong = new JMenu("<html><p style='text-align:center; width:75px'>H??? th???ng</p></html>") {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(this, "helo ");
			}
		};
		menuDanhMuc = new JMenu("<html><p style='text-align:center; width:75px'>Danh m???c</p></html>");
		menuXuLy = new JMenu("<html><p style='text-align:center; width:75px'X??? l??</p></html>");
		menuThongKe = new JMenu("<html><p style='text-align:center; width:75px'>Th???ng k???</p></html>");
		menuTimKiem = new JMenu("<html><p style='text-align:center; width:75px'>T??m ki???m</p></html>");
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
		menuLinhKien = new JMenuItem("Qu???n l?? linh ki???n");
		menuLinhKien.setPreferredSize(new Dimension(200, 30));

		menuKhachHang = new JMenuItem("Qu???n l?? kh??ch h??ng");
		menuKhachHang.setPreferredSize(new Dimension(200, 30));

		menuNhanVien = new JMenuItem("Qu???n l?? nh??n vi??n");
		menuNhanVien.setPreferredSize(new Dimension(200, 30));

		menuNhaCungCap = new JMenuItem("Danh s??ch nh?? cung c???p");
		menuNhaCungCap.setPreferredSize(new Dimension(200, 30));

		menuHoaDon = new JMenuItem("Qu???n l?? ho?? ????n");
		menuHoaDon.setPreferredSize(new Dimension(200, 30));
		menuQuanLyChiTietHoaDon = new JMenuItem("Qu???n l?? chi ti???t ho?? ????n");
		menuQuanLyChiTietHoaDon.setPreferredSize(new Dimension(200, 30));
		menuLoaiLinhKien = new JMenuItem("Danh s??ch lo???i linh ki???n");
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
				+ "center; margin-top:5px;\"> &copy; Nh??m 8: qu???n l?? linh ki??n - Gi???ng vi??n h?????ng d???n T??n Long Ph?????c "
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

	public void CenterNhanVien() throws SQLException {
		JLabel lbMaNhanVien = new JLabel("M?? nh??n vi??n:");
		JLabel lbHo = new JLabel("H???:");
		JLabel lbTen = new JLabel("T??n:");
		JLabel lbsdt = new JLabel("S??? ??i???n tho???i:");
		JLabel lbDiaChi = new JLabel("?????a ch???:");
		JLabel lbEmail = new JLabel("Email:");
		JLabel lbGioiTinh = new JLabel("Gi???i t??nh:");
		JLabel lbNgayLamViec = new JLabel("Ng??y v??o l??m:");
		JLabel lbSoCCCD = new JLabel("S??? CCCD:");
		JLabel lbMatKhau = new JLabel("M???t kh???u:");
		JLabel lbPhongBan = new JLabel("T??n ph??ng ban:");
		JPanel pnMain = new JPanel();
		this.add(pnMain, BorderLayout.CENTER);
		// Header
		JLabel lbQuanLyNhanVien = new JLabel("QU???N L?? NH??N VI??N", SwingConstants.CENTER);
		lbQuanLyNhanVien.setFont(new Font("Arial", Font.BOLD, 20));
		lbQuanLyNhanVien.setForeground(Color.RED);
		JPanel pnH = new JPanel();
		pnH.setLayout(new BoxLayout(pnH, BoxLayout.Y_AXIS));
		// pn0
		JPanel pn0 = new JPanel();
		pn0.add(lbQuanLyNhanVien);
		// p1
		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
		pn1.add(lbMaNhanVien);
		lbMaNhanVien.setPreferredSize(new Dimension(120, 25));
		txtMaNhanVien = new JTextField(30);
		pn1.add(txtMaNhanVien);
		pn1.add(Box.createRigidArea(new Dimension(700, 25)));
		// pn 2
		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));
		pn2.add(lbHo);
		lbHo.setPreferredSize(new Dimension(120, 25));
		txtHo = new JTextField(30);
		pn2.add(txtHo);
		pn2.add(Box.createRigidArea(new Dimension(25, 25)));
		pn2.add(lbTen);
		lbTen.setPreferredSize(new Dimension(120, 25));
		txtTen = new JTextField(30);
		pn2.add(txtTen);
		pn2.add(Box.createRigidArea(new Dimension(80, 25)));
		// pn3
		JPanel pn3 = new JPanel();
		pn3.setLayout(new BoxLayout(pn3, BoxLayout.X_AXIS));
		pn3.add(lbsdt);
		lbsdt.setPreferredSize(new Dimension(120, 25));
		txtSdt = new JTextField(30);
		pn3.add(txtSdt);
		pn3.add(Box.createRigidArea(new Dimension(25, 25)));
		pn3.add(lbDiaChi);
		lbDiaChi.setPreferredSize(new Dimension(120, 25));
		txtDiaChi = new JTextField(30);
		pn3.add(txtDiaChi);
		pn3.add(Box.createRigidArea(new Dimension(80, 25)));
		// pn4
		JPanel pn4 = new JPanel();
		pn4.setLayout(new BoxLayout(pn4, BoxLayout.X_AXIS));
		pn4.add(lbEmail);
		lbEmail.setPreferredSize(new Dimension(120, 25));
		txtEmail = new JTextField(30);
		pn4.add(txtEmail);
		pn4.add(Box.createRigidArea(new Dimension(25, 25)));
		pn4.add(lbGioiTinh);
		lbGioiTinh.setPreferredSize(new Dimension(120, 25));
		ButtonGroup group = new ButtonGroup();
		rdNam = new JRadioButton("Nam");
		rdNu = new JRadioButton("N???");
		group.add(rdNu);
		group.add(rdNam);
		rdNam.setSelected(true);
		pn4.add(rdNam);
		pn4.add(rdNu);
		pn4.add(Box.createRigidArea(new Dimension(377, 25)));
		// pn5
		JPanel pn5 = new JPanel();
		pn5.setLayout(new BoxLayout(pn5, BoxLayout.X_AXIS));
		pn5.add(lbNgayLamViec);
		lbNgayLamViec.setPreferredSize(new Dimension(120, 25));
		txtDate = new JDateChooser();
		txtDate.setDateFormatString("dd-MM-yyyy");
		pn5.add(txtDate);
		pn5.add(Box.createRigidArea(new Dimension(250, 25)));
		pn5.add(lbSoCCCD);
		lbSoCCCD.setPreferredSize(new Dimension(120, 25));
		txtSoCCCD = new JTextField(30);
		pn5.add(txtSoCCCD);
		pn5.add(Box.createRigidArea(new Dimension(80, 25)));
		// pn 6
		JPanel pn6 = new JPanel();
		pn6.setLayout(new BoxLayout(pn6, BoxLayout.X_AXIS));
		pn6.add(lbMatKhau);
		lbMatKhau.setPreferredSize(new Dimension(120, 25));
		txtMatKhau = new JPasswordField(30);
		pn6.add(txtMatKhau);
		pn6.add(Box.createRigidArea(new Dimension(25, 25)));
		JLabel lbNhapLai = new JLabel("Nh???p l???i m???t kh???u:");
		pn6.add(lbNhapLai);
		lbNhapLai.setPreferredSize(new Dimension(125, 25));
		txtNhapLai = new JPasswordField(30);
		txtMatKhau.setEchoChar('*');
		txtNhapLai.setEchoChar('*');
		pn6.add(txtNhapLai);
		pn6.add(Box.createRigidArea(new Dimension(80, 25)));
		// pn 07
		JPanel pn7 = new JPanel();
		pn7.setLayout(new BoxLayout(pn7, BoxLayout.X_AXIS));
		pn7.add(lbPhongBan);
		lbPhongBan.setPreferredSize(new Dimension(120, 25));
		cbPhongBan = new JComboBox<String>();
		PhongBan_DAO pbDao = new PhongBan_DAO();
		for (PhongBan pb : pbDao.getAllPhongBan()) {
			cbPhongBan.addItem(pb.getTenPhongBan());
		}
		pn7.add(cbPhongBan);
		pn7.add(Box.createRigidArea(new Dimension(500, 25)));
		// pn8
		JPanel pn8 = new JPanel();
		pn8.setLayout(new BoxLayout(pn8, BoxLayout.X_AXIS));
		btnThem = new JButton("Th??m");
		btnXoa = new JButton("Xo??");
		btnCapNhat = new JButton("L??u");
		btnTim = new JButton("T??m");
		if (!(this.maNhanVien.contains("AD"))) {
			this.txtHo.setEditable(false);
			this.txtTen.setEditable(false);
			this.txtSdt.setEditable(false);
			this.txtDiaChi.setEditable(false);
			this.txtEmail.setEditable(false);
			this.rdNam.setEnabled(false);
			this.rdNu.setEnabled(false);
			this.txtDate.setEnabled(false);
			this.txtSoCCCD.setEditable(false);
			this.txtMatKhau.setEditable(false);
			this.txtNhapLai.setEditable(false);
			this.cbPhongBan.setEditable(false);
		} else {
			pn8.add(btnThem);
			pn8.add(btnXoa);
			pn8.add(btnCapNhat);
			pn8.add(btnTim);
		}
		btnThem.setMaximumSize(new Dimension(85, 25));
		btnXoa.setMaximumSize(new Dimension(85, 25));
		btnCapNhat.setMaximumSize(new Dimension(85, 25));
		btnTim.setMaximumSize(new Dimension(85, 25));
		// pnh
		pnH.add(pn0);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn1);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn2);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn3);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn4);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn5);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn6);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn7);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		pnH.add(pn8);
		pnH.add(Box.createRigidArea(new Dimension(0, 10)));
		TitledBorder title = new TitledBorder(BorderFactory.createLineBorder(Color.red),
				"Nh???p v??o th??ng tin nh??n vi??n");
		pnH.setBorder(title);
		// pnCenter
		String[] s = { "M?? nh??n vi??n", "H???", "T??n", "S??? ??i???n tho???i", "?????a ch???", "Email", "Gi???i t??nh", "Ng??y v??o l??m",
				"SCCCD", "M???t kh???u", "Ph??ng ban" };
		model = new DefaultTableModel(s, 0);
		table = new JTable(model);
		JScrollPane crollPanelTable = new JScrollPane(table);
		crollPanelTable.setPreferredSize(new Dimension(1180, 500));
		// pmain add
		pnMain.add(pnH, BorderLayout.NORTH);
		pnMain.add(crollPanelTable, BorderLayout.CENTER);
		loadData();
		this.btnThem.addActionListener(this);
		this.btnCapNhat.addActionListener(this);
		this.btnXoa.addActionListener(this);
		this.btnTim.addActionListener(this);
		this.table.addMouseListener(this);
		System.out.println(this.maNhanVien);
		if (!(this.maNhanVien.contains("AD"))) {
			this.btnThem.setEnabled(false);
			this.btnCapNhat.setEnabled(false);
			this.btnXoa.setEnabled(false);
			this.txtMaNhanVien.setEditable(false);
			this.txtHo.setEditable(false);
			this.txtTen.setEditable(false);
			this.txtSdt.setEditable(false);
			this.txtDiaChi.setEditable(false);
			this.txtEmail.setEditable(false);
			this.rdNam.setEnabled(false);
			this.rdNu.setEnabled(false);
			this.txtDate.setEnabled(false);
			this.txtSoCCCD.setEditable(false);
			this.txtMatKhau.setEditable(false);
			this.txtNhapLai.setEditable(false);
			this.cbPhongBan.setEditable(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equalsIgnoreCase("Th??m")) {
			try {
				them();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (src.equalsIgnoreCase("Hu???")) {
			huy();
		}
		if (src.equalsIgnoreCase("C???p nh???t")) {
			capNhat();
		}
		if (src.equalsIgnoreCase("L??u")) {
			try {
				luu();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (src.equalsIgnoreCase("Xo??")) {
			xoa();
		}
		if (src.equalsIgnoreCase("T??m")) {
			String maNhanVien = JOptionPane.showInputDialog(this, "M???i b???n nh???p m?? nh??n vi??n");
			NhanVien_DAO nhanVien = new NhanVien_DAO();
			NhanVien nv = nhanVien.getNhanVien(maNhanVien);
			if (nv == null) {
				JOptionPane.showMessageDialog(this, "Kh??ng t???n t???i m?? nh??n vi??n c???n t??m");
			} else {
				String ma = nv.getMaNhanVien();
				String ho = nv.getHo();
				String ten = nv.getTen();
				String sdt = nv.getSdt();
				String diaChi = nv.getDiaChi();
				String email = nv.getEmail();
				String gioiTinh = nv.isGioiTinh() ? "Nam" : "N???";
				String ngayVaoLam = nv.getNgayVaoLam().toString();
				String sCCCD = nv.getSoCCCD();
				String matKhau = nv.getMatKhau();
				String maphongBan = nv.getMaPhongBan();
				String tenPhongBan ="";
				PhongBan_DAO pb = new PhongBan_DAO();
				try {
					List<PhongBan> phongBanTheoMa = pb.getAllPhongBan();
					for (PhongBan phongBan2 : phongBanTheoMa) {
						if (phongBan2.getMaPhongBan().equals(maphongBan)) {
							tenPhongBan=phongBan2.getTenPhongBan();

						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while(this.model.getRowCount()>0) {
					this.model.removeRow(0);
				}
				this.model.addRow(new Object[] {ma,ho,ten,sdt,diaChi,email,gioiTinh,ngayVaoLam,sCCCD,matKhau,tenPhongBan});
				this.btnThem.setText("Hu???");
			}
		}
		Object o = e.getSource();
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
			new QuanLyLoaiLinhKien(maNhanVien,tenNhanVien).setVisible(true);
			this.setVisible(false);
		}
		else if (e.getSource().equals(this.menuNhaCungCap)) {
			new QuanLyNhaCungCap(maNhanVien, tenNhanVien);
			this.dispose();
		}

	}

	public void huy() {
		this.txtMaNhanVien.setText("");
		this.txtMaNhanVien.setEditable(true);
		this.txtHo.setText("");
		this.txtHo.setEditable(true);
		this.txtDate.setDate(null);
		this.txtDate.setEnabled(true);
		this.txtTen.setText("");
		this.txtTen.setEditable(true);
		this.txtSdt.setText("");
		this.txtSdt.setEditable(true);
		this.txtEmail.setText("");
		this.txtEmail.setEditable(true);
		this.rdNam.setSelected(true);
		this.rdNam.setEnabled(true);
		this.rdNu.setEnabled(true);
		this.txtSoCCCD.setText("");
		this.txtSoCCCD.setEditable(true);
		this.cbPhongBan.setSelectedIndex(0);
		this.cbPhongBan.setEditable(true);
		this.txtMatKhau.setText("");
		this.txtMatKhau.setEditable(true);
		this.txtNhapLai.setText("");
		this.txtNhapLai.setEditable(true);
		this.txtDiaChi.setText("");
		this.txtDiaChi.setEditable(true);
		this.btnThem.setText("Th??m");
		this.btnCapNhat.setText("C???p nh???t");
		try {
			loadData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void
	public boolean them() throws SQLException {
		String maNhanVien = this.txtMaNhanVien.getText().trim();
		String ho = this.txtHo.getText().trim();
		String ten = this.txtTen.getText().trim();
		String sdt = this.txtSdt.getText().trim();
		String diaChi = this.txtDiaChi.getText().trim();
		String email = this.txtEmail.getText().trim();
		boolean gioiTinh = true;
		if (this.rdNu.isSelected()) {
			gioiTinh = false;
		}
		java.util.Date ngayVaoLam = this.txtDate.getDate();
		String soCCCD = this.txtSoCCCD.getText();
		String matKhau = new String(this.txtMatKhau.getPassword());
		String phongBan = this.cbPhongBan.getSelectedItem().toString();
		if (!(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$"))) {
			JOptionPane.showMessageDialog(this, "M?? nh??n vi??n ph???i theo m???u NV|AD+[0-9]{6}");
			System.out.println(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$") + "");
			return false;
		}
		if (!(ho.matches("^[A-Z][a-z]+$"))) {
			System.out.println(ho);
			JOptionPane.showMessageDialog(this, "H??? nh??n vi??n ph???i ???????c vi???t hoa ch??? c??i ?????u");
			return false;
		}
		if (!(ten.matches("^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$"))) {
			JOptionPane.showMessageDialog(this,
					"T??n nh??n vi??n ph???i ???????c vi???t hoa ch??? c??i ?????u m???i t??c ph???i c?? 1 kho???ng tr???ng");
			return false;
		}
		if (!(sdt.matches("^[0-9]{9,11}$"))) {
			JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng h???p l???");
		}
		if (!(email.matches("^[a-z]\\w+@(gmail|email).com$"))) {
			JOptionPane.showMessageDialog(this, "Email ph???i theo m???u nguyenvan@gmail.com|@email.com");
			return false;
		}
		if (ngayVaoLam.after(new java.util.Date())) {
			JOptionPane.showMessageDialog(this, "Ng??y v??o l??m kh??ng h???p l???");
			return false;
		}
		if (!soCCCD.matches("^[0-9]{12}$")) {
			JOptionPane.showMessageDialog(this, "S??? CCCD kh??ng h??p l???, phai 0->9 {12}");
			return false;
		}
		this.nhanVienDao = new NhanVien_DAO();
		List<NhanVien> nhanVien = this.nhanVienDao.getAllNhanVien();
		for (NhanVien nhanVien2 : nhanVien) {
			if (nhanVien2.getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
				JOptionPane.showMessageDialog(this, "M?? nh??n vi??n ???? t???n t???i");
				return false;
			}
		}
		if (!(matKhau.equals(this.txtNhapLai.getText()))) {
			System.out.println(matKhau);
			System.out.println(this);
			JOptionPane.showMessageDialog(this, "M???t kh???u nh???p l???i kh??ng kh???p");
			return false;
		}
		String maPhongBan = "";
		PhongBan_DAO phongban = new PhongBan_DAO();
		List<PhongBan> dsPb = phongban.getAllPhongBan();
		for (PhongBan phongBan2 : dsPb) {
			if (phongBan2.getTenPhongBan().equals(phongBan)) {
				maPhongBan = phongBan2.getMaPhongBan();
			}
		}
		NhanVien nhanVien2 = new NhanVien(maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, ngayVaoLam, soCCCD,
				matKhau, maPhongBan);
		this.nhanVienDao.themNhanVien(nhanVien2);
		loadData();
		JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng");
		return true;
	}

	public void capNhat() {
		this.txtMaNhanVien.setEditable(false);
		this.txtHo.setEditable(true);
		this.txtTen.setEditable(true);
		this.txtSdt.setEditable(true);
		this.txtDiaChi.setEditable(true);
		this.txtEmail.setEditable(true);
		this.rdNam.setEnabled(true);
		this.rdNu.setEnabled(true);
		this.txtDate.setEnabled(true);
		this.txtSoCCCD.setEditable(true);
		this.txtMatKhau.setEditable(true);
		this.txtNhapLai.setEditable(true);
		this.cbPhongBan.setEditable(true);
		this.btnCapNhat.setText("L??u");
	}

	public boolean luu() throws SQLException {
		String maNhanVien = this.txtMaNhanVien.getText().trim();
		String ho = this.txtHo.getText().trim();
		String ten = this.txtTen.getText().trim();
		String sdt = this.txtSdt.getText().trim();
		String diaChi = this.txtDiaChi.getText().trim();
		String email = this.txtEmail.getText().trim();
		boolean gioiTinh = true;
		if (this.rdNu.isSelected()) {
			gioiTinh = false;
		}
		java.util.Date ngayVaoLam = this.txtDate.getDate();
		String soCCCD = this.txtSoCCCD.getText();
		String matKhau = new String(this.txtMatKhau.getPassword());
		String phongBan = this.cbPhongBan.getSelectedItem().toString();
		if (!(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$"))) {
			JOptionPane.showMessageDialog(this, "M?? nh??n vi??n ph???i theo m???u NV|AD+[0-9]{6}");
			System.out.println(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$") + "");
			return false;
		}
		if (!(ho.matches("^[A-Z][a-z]+$"))) {
			System.out.println(ho);
			JOptionPane.showMessageDialog(this, "H??? nh??n vi??n ph???i ???????c vi???t hoa ch??? c??i ?????u");
			return false;
		}
		if (!(ten.matches("^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$"))) {
			JOptionPane.showMessageDialog(this,
					"T??n nh??n vi??n ph???i ???????c vi???t hoa ch??? c??i ?????u m???i t??c ph???i c?? 1 kho???ng tr???ng");
			return false;
		}
		if (!(sdt.matches("^[0-9]{9,11}$"))) {
			JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng h???p l???");
		}
		if (!(email.matches("^[a-zA-Z0-9]\\w+@(gmail|email).com$"))) {
			JOptionPane.showMessageDialog(this, "Email ph???i theo m???u nguyenvan@gmail.com|@email.com");
			return false;
		}
		if (ngayVaoLam.after(new java.util.Date())) {
			JOptionPane.showMessageDialog(this, "Ng??y v??o l??m kh??ng h???p l???");
			return false;
		}
		if (!soCCCD.matches("^[0-9]{12}$")) {
			JOptionPane.showMessageDialog(this, "S??? CCCD kh??ng h??p l???, phai 0->9 {12}");
			return false;
		}
		if (!(matKhau.equals(String.valueOf(this.txtNhapLai.getPassword())))) {
			
			JOptionPane.showMessageDialog(this, "M???t kh???u nh???p l???i kh??ng kh???p");
			return false;
		}
		String maPhongBan = "";
		PhongBan_DAO phongban = new PhongBan_DAO();
		List<PhongBan> dsPb = phongban.getAllPhongBan();
		for (PhongBan phongBan2 : dsPb) {
			if (phongBan2.getTenPhongBan().equals(phongBan)) {
				maPhongBan = phongBan2.getMaPhongBan();
			}
		}
		NhanVien nhanVien2 = new NhanVien(maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, ngayVaoLam, soCCCD,
				matKhau, maPhongBan);
		this.nhanVienDao.capNhatNhanVien(nhanVien2);
		loadData();
		JOptionPane.showMessageDialog(this, "C???p nh???t th??nh c??ng");
		return true;
	}

	public boolean xoa() {
		if (this.table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Ban ph???i ch???n d??ng c???n xo??");
			return false;
		}
		int row = this.table.getSelectedRow();
		String maNhanVien = this.model.getValueAt(row, 0).toString();
		if (JOptionPane.showConfirmDialog(this, "B???n x??c nh???n xo?? nh??n vi??n ???? ch???n", "Confirm",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			NhanVien_DAO nhanvien = new NhanVien_DAO();
			try {
				if (nhanvien.xoaNhanVien(maNhanVien)) {
					loadData();
					JOptionPane.showMessageDialog(this, "Xo?? nh??n vi??n th??nh c??ng");
					huy();
					return true;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "B??? x???y ra l??i khi xo??", "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void main(String[] args) throws SQLException {
		new QuanLyNhanVien("AD123456", "Hello").setVisible(true);
	}

	public void loadData() throws SQLException {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.nhanVienDao = new NhanVien_DAO();
		List<NhanVien> dsNhanVien = this.nhanVienDao.getAllNhanVien();
		for (NhanVien nhanvien : dsNhanVien) {
			String maNhanVien = nhanvien.getMaNhanVien();
			String ho = nhanvien.getHo();
			String ten = nhanvien.getTen();
			String sdt = nhanvien.getSdt();
			String diaChi = nhanvien.getDiaChi();
			String email = nhanvien.getEmail();
			boolean gender = nhanvien.isGioiTinh();
			String gioiTinh = "Nam";
			if (!gender) {
				gioiTinh = "N???";
			}
			String ngayVaoLam = nhanvien.getNgayVaoLam().toString();
			String soCCCD = nhanvien.getSoCCCD();
			String matKhau = nhanvien.getMatKhau();
			String pb = nhanvien.getMaPhongBan();
			String phongBan = "";
			PhongBan_DAO phongbanDao = new PhongBan_DAO();
			List<PhongBan> dsPhongBan = phongbanDao.getAllPhongBan();
			for (PhongBan pb1 : dsPhongBan) {
				if (pb1.getMaPhongBan().equalsIgnoreCase(pb)) {
					phongBan = pb1.getTenPhongBan();
					break;
				}
			}
			model.addRow(new Object[] { maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, ngayVaoLam, soCCCD, matKhau,
					phongBan });
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = this.table.getSelectedRow();
		this.txtMaNhanVien.setText(this.model.getValueAt(row, 0).toString());
		this.txtHo.setText(this.model.getValueAt(row, 1).toString());
		this.txtTen.setText(this.model.getValueAt(row, 2).toString());
		this.txtSdt.setText(this.model.getValueAt(row, 3).toString());
		this.txtDiaChi.setText(this.model.getValueAt(row, 4).toString());
		this.txtEmail.setText(this.model.getValueAt(row, 5).toString());
		if (this.model.getValueAt(row, 6).toString().equalsIgnoreCase("Nam")) {
			this.rdNam.setSelected(true);
		} else {
			this.rdNu.setSelected(true);
		}
//		this.txtDate.set(new java.util.Date(this.model.getValueAt(row, 7).toString()));
		String d = this.model.getValueAt(row, 7).toString();
		try {
			java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(d);
			this.txtDate.setDate(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.txtSoCCCD.setText(this.model.getValueAt(row, 8).toString());
		this.txtMatKhau.setText(this.model.getValueAt(row, 9).toString());
		this.txtNhapLai.setText(this.model.getValueAt(row, 9).toString());
		this.cbPhongBan.setSelectedItem(this.model.getValueAt(row, 10));
		this.btnThem.setText("Hu???");
		this.txtMaNhanVien.setEditable(false);
		this.txtHo.setEditable(false);
		this.txtTen.setEditable(false);
		this.txtSdt.setEditable(false);
		this.txtDiaChi.setEditable(false);
		this.txtEmail.setEditable(false);
		this.rdNam.setEnabled(false);
		this.rdNu.setEnabled(false);
		this.txtDate.setEnabled(false);
		this.txtSoCCCD.setEditable(false);
		this.txtMatKhau.setEditable(false);
		this.txtNhapLai.setEditable(false);
		this.cbPhongBan.setEditable(false);
		this.btnCapNhat.setText("C???p nh???t");
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
			this.setVisible(false);
			return;
		} else if (e.getSource().equals(this.menuHeThong)) {
			if (JOptionPane.showConfirmDialog(this, "B???n x??c nh???n mu???n tho??t ch????ng tr??nh", "",
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
