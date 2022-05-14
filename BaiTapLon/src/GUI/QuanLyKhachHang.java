package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

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
import javax.swing.JRadioButton;
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
import Entity.KhachHang;
import dao.KhachHang_DAO;
import dao.LoaiLinhKien_DAO;
import dao.NhanVien_DAO;

public class QuanLyKhachHang extends JFrame  implements ActionListener, MouseListener,MenuListener{
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
	private JButton btnTim;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private JTextField txtMa;
	private JTextField txtHo;
	private JTextField txtDiaChi;
	private JTextField txtTen;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private JTextField txtGioiTinh;
	private DefaultTableModel model;
	private JTable table;
	public QuanLyKhachHang(String maNhanVien,String tenNhanVien) {
		ConnectDB.getInstance().connect();
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		this.setTitle("Quản lý linh kiện");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setCursor(Cursor.HAND_CURSOR);
	
		init();
		KhachHangCenter();
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


	public void KhachHangCenter() {
		JPanel pnMain = new JPanel();
		TitledBorder tieuDe = new TitledBorder(BorderFactory.createLineBorder(Color.RED), "Thông tin khách hàng");
		tieuDe.setTitleFont(new Font("arial", Font.BOLD, 14));
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(pnMain);
		scroll.setBorder(tieuDe);
		this.add(scroll, BorderLayout.CENTER);
		JLabel lbma = new JLabel("Mã khách hàng:");
		JLabel lbHo = new JLabel("Họ:");
		JLabel lbTen = new JLabel("Tên:");
		JLabel lbDiaChi = new JLabel("Địa chỉ:");
		JLabel lbSdt = new JLabel("Số điện thoại:");
		JLabel lbEmail = new JLabel("Email:");
		JLabel lbGioiTinh= new JLabel("Giới tính:");
		 txtMa = new JTextField(12);
		 txtHo = new JTextField(12);
		 txtTen = new JTextField(12);
		 txtDiaChi = new JTextField(12);
		 txtSdt = new JTextField(12);
		 txtEmail = new JTextField(12);
		 txtGioiTinh = new JTextField(12);
		// pn top
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.X_AXIS));
		pnTop.setMaximumSize(new Dimension(1000, 270));
//		pnTop.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
//		JPanel
		JPanel pnCol1 = new JPanel();
		pnCol1.setMaximumSize(new Dimension(250, 270));
		JLabel lbAnh = new JLabel("noi nay de anh");
		pnCol1.add(lbAnh);
		pnCol1.setBorder(BorderFactory.createLineBorder(Color.red));

		// pncol 2
		JPanel pnCol2 = new JPanel();
		pnCol2.setLayout(new BoxLayout(pnCol2, BoxLayout.Y_AXIS));
		pnCol2.setMaximumSize(new Dimension(500, 270));
		JPanel pnCol2_1 = new JPanel();
		pnCol2_1.setLayout(new BoxLayout(pnCol2_1, BoxLayout.X_AXIS));
		pnCol2_1.add(lbma);
		lbma.setPreferredSize(new Dimension(100, 25));
		pnCol2_1.add(Box.createRigidArea(new Dimension(20, 0)));
		pnCol2_1.add(txtMa);
		txtMa.setMaximumSize(new Dimension(500, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_1);

		JPanel pnCol2_2 = new JPanel();
		pnCol2_2.setLayout(new BoxLayout(pnCol2_2, BoxLayout.X_AXIS));
		pnCol2_2.add(lbHo);
		lbHo.setPreferredSize(new Dimension(100, 25));
		pnCol2_2.add(Box.createRigidArea(new Dimension(89, 0)));
		pnCol2_2.add(txtHo);
		txtHo.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_2);

		JPanel pnCol2_3 = new JPanel();
		pnCol2_3.setLayout(new BoxLayout(pnCol2_3, BoxLayout.X_AXIS));
		pnCol2_3.add(lbTen);
		txtHo.setPreferredSize(new Dimension(100, 27));
		pnCol2_3.add(Box.createRigidArea(new Dimension(85, 0)));
		pnCol2_3.add(txtTen);
		txtTen.setMaximumSize(new Dimension(500, 30));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_3);

		JPanel pnCol2_4 = new JPanel();
		pnCol2_4.setLayout(new BoxLayout(pnCol2_4, BoxLayout.X_AXIS));
		pnCol2_4.add(lbDiaChi);
		lbDiaChi.setPreferredSize(new Dimension(100, 25));
		pnCol2_4.add(Box.createRigidArea(new Dimension(66, 0)));
		pnCol2_4.add(txtDiaChi);
		txtDiaChi.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_4);

		JPanel pnCol2_5 = new JPanel();
		pnCol2_5.setLayout(new BoxLayout(pnCol2_5, BoxLayout.X_AXIS));
		pnCol2_5.add(lbSdt);
		lbSdt.setPreferredSize(new Dimension(100, 25));
		pnCol2_5.add(Box.createRigidArea(new Dimension(30, 0)));
		pnCol2_5.add(txtSdt);
		txtSdt.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_5);

		JPanel pnCol2_6 = new JPanel();
		pnCol2_6.setLayout(new BoxLayout(pnCol2_6, BoxLayout.X_AXIS));
		pnCol2_6.add(lbEmail);
		lbEmail.setPreferredSize(new Dimension(100, 25));
		pnCol2_6.add(Box.createRigidArea(new Dimension(73, 0)));
		pnCol2_6.add(txtEmail);
		txtEmail.setMaximumSize(new Dimension(400, 25));
		pnCol2.add(Box.createRigidArea(new Dimension(0, 10)));
		pnCol2.add(pnCol2_6);
		
		
		rdNam = new JRadioButton("Nam");
		rdNu = new JRadioButton("Nữ");
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdNu);
		btnGroup.add(rdNam);
		rdNam.setSelected(true);
		JPanel pnCol2_7 = new JPanel();
		pnCol2_7.setLayout(new BoxLayout(pnCol2_7, BoxLayout.X_AXIS));
		pnCol2_7.add(lbGioiTinh);
		lbGioiTinh.setPreferredSize(new Dimension(100, 25));
		pnCol2_7.add(Box.createRigidArea(new Dimension(50, 0)));
		pnCol2_7.add(rdNam);
		pnCol2_7.add(Box.createRigidArea(new Dimension(10, 0)));
		pnCol2_7.add(rdNu);
		pnCol2_7.add(Box.createRigidArea(new Dimension(300, 0)));
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
//		pnTop.add(pnCol1);
		pnTop.add(Box.createRigidArea(new Dimension(220, 0)));
		pnTop.add(pnCol2);
		pnTop.add(pnCol3);
		pnMain.add(pnTop);
		/// table
		String[] s = { "Mã khách hàng", "Họ", "Tên", "Số điện thoại", "Địa chỉ", "Email"," Giới tính" };
		model = new DefaultTableModel(s, 0);

		table = new JTable(model);
		JScrollPane scrolTable = new JScrollPane(table);
		pnMain.add(scrolTable);
		this.btnThem.addActionListener(this);
		this.btnCapNhat.addActionListener(this);
		this.btnXoa.addActionListener(this);
		this.btnTim.addActionListener(this);
		this.table.addMouseListener(this);
		btnCapNhat.setEnabled(false);
		loadData();
	}
	public boolean validator() {
		String maNV = txtMa.getText().trim();
		String ho = txtHo.getText().trim();
		String ten = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String sdt = txtSdt.getText().trim();
		String email = txtEmail.getText().trim();
		if (maNV.isEmpty() || ho.isEmpty() || ten.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không field nào được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (!maNV.matches("^KH\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã Khách hàng bắt đầu bằng KH và theo sau là 3 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!ho.matches("^[A-Z][a-z]+$")) {
			JOptionPane.showMessageDialog(null, "Họ phải bắt đàu bằng in hoa, có ít nhất 2 kí tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!ten.matches("^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$")) {
			JOptionPane.showMessageDialog(null, "Tên được viết hoa chữ cái đầu mỗi tưc phải có 1 khoảng trắng", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!diaChi.matches("^[\\w\\s\\.\\,\\-\\*\\_\\&]{3,}$")) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!sdt.matches("^0[0-9]{9,11}$")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại có 9 đến 11 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!email.matches("^[a-zA-Z0-9]\\w+@(gmail|email).com$")) {
			JOptionPane.showMessageDialog(null, "Email phải theo mẫu nguyenvan@gmail.com|@email.com", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Thêm")) {
			if (!validator()) {
				return;
			}
			them();
		}
		if(e.getSource().equals(this.btnXoa)) {
			xoa();
		}
		if(e.getActionCommand().equals("Cập nhật")) {
			capNhat();
		}
		if(e.getActionCommand().equals("Huỷ")) {
			huy();
		}
		if(e.getActionCommand().equals("Lưu")) {
			luu();
			huy();
		}
		if(e.getActionCommand().equals("Tìm")) {
			String maKhachHang = JOptionPane.showInputDialog("Nhập mã khách hàng bạn muốn tìm");
			while (this.table.getRowCount()>0) {
				this.model.removeRow(0);
			}
			KhachHang_DAO khachHang = new KhachHang_DAO();
			List<KhachHang> kh = khachHang.getKhachHangTheoMa(maKhachHang);
			if(kh.size()==0) {
				JOptionPane.showMessageDialog(this,"Không tồn tại mã khách hàng phù hợp");
				loadData();
				return;
			}
			while(this.model.getRowCount() >0) {
				this.model.removeRow(0);
			}
			for (KhachHang k : kh) {
				String makh=k.getMaKhachHang();
				String ho =k.getHo();
				String ten=k.getTen();
				String sdt=k.getSdt();
				String diaChi=k.getDiaChi();
				String email =k.getEmail();
				String gioiTinh = k.isGioTinh()?"Nam":"Nữ";
				this.model.addRow(new Object[]{makh,ho,ten,diaChi,sdt,email,gioiTinh});
				this.btnThem.setText("Huỷ");
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
	public void loadData() {
		while (this.table.getRowCount()>0) {
			this.model.removeRow(0);
		}
		KhachHang_DAO khachHang = new KhachHang_DAO();
		List<KhachHang> kh = khachHang.getAllKhachHang();
		for (KhachHang k : kh) {
			String makh=k.getMaKhachHang();
			String ho =k.getHo();
			String ten=k.getTen();
			String sdt=k.getSdt();
			String diaChi=k.getDiaChi();
			String email =k.getEmail();
			String gioiTinh = k.isGioTinh()?"Nam":"Nữ";
			this.model.addRow(new Object[]{makh,ho,ten,sdt,diaChi,email,gioiTinh});
		}
	}
	public void capNhat() {
		this.btnThem.setText("Huỷ");
		this.btnTim.setEnabled(false);
		this.btnXoa.setEnabled(false);
		this.btnCapNhat.setText("Lưu");
		this.txtMa.setEditable(false);
		this.txtHo.setEditable(true);
		this.txtTen.setEditable(true);
		this.txtSdt.setEditable(true);
		this.txtDiaChi.setEditable(true);
		this.txtEmail.setEditable(true);
		rdNam.setEnabled(true);
		rdNu.setEnabled(true);
		}
	public void huy() {
		this.btnThem.setText("Thêm");
		this.btnTim.setEnabled(true);
		this.btnXoa.setEnabled(true);
		this.btnCapNhat.setText("Cập nhật");
		this.txtMa.setEditable(true);
		this.txtMa.setEditable(true);
		this.txtHo.setEditable(true);
		this.txtTen.setEditable(true);
		this.txtSdt.setEditable(true);
		this.txtDiaChi.setEditable(true);
		this.txtEmail.setEditable(true);
		rdNam.setEnabled(true);
		rdNu.setEnabled(true);
		this.btnTim.setEnabled(true);
		this.txtMa.setText("");
		this.txtHo.setText("");
		this.txtTen.setText("");
		this.txtSdt.setText("");
		this.txtDiaChi.setText("");
		this.txtEmail.setText("");
		rdNam.setSelected(true);
		this.btnCapNhat.setEnabled(false);
		loadData();
	}
	public void them() {
		String maKhacHang =this.txtMa.getText().trim();
		String ho =this.txtHo.getText().trim();
		String ten =this.txtTen.getText().trim();
		String sdt =this.txtSdt.getText().trim();
		String diaChi =this.txtDiaChi.getText().trim();
		String email =this.txtEmail.getText().trim();
		boolean gioTinh = this.rdNam.isSelected();
		KhachHang kh = new KhachHang(maKhacHang, ho, ten, sdt, diaChi, email, gioTinh);
		KhachHang_DAO khDao = new KhachHang_DAO();
		if(khDao.themKhachHang(kh)) {
			JOptionPane.showMessageDialog(this, "Thêm thành công");
			loadData();
		}else {
			JOptionPane.showMessageDialog(this, "Thêm thất bại, mã khách hàng đã tồn tại");
		}
	}
	public void xoa() {
		if(this.table.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(this, "Bạn cần chọn dòng muốn xoá");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "Bạn xác nhận xoá dòng đẫ chon","Confirm",
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				String maKachHang = this.model.getValueAt(table.getSelectedRow(),0 ).toString();
				KhachHang_DAO nhanDao = new KhachHang_DAO();
				try {
					if(nhanDao.xoaKhachHang(maKachHang)) {
						loadData();
						JOptionPane.showMessageDialog(this, "Xoá thành công");
					}else {
						JOptionPane.showMessageDialog(this, "Xoá không thành công");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Xyar ra lỗi khi xoá");
					e.printStackTrace();
				}
				huy();
		}
		
	}
	
	public void luu() {
		String maKhacHang =this.txtMa.getText().trim();
		String ho =this.txtHo.getText().trim();
		String ten =this.txtTen.getText().trim();
		String sdt =this.txtSdt.getText().trim();
		String diaChi =this.txtDiaChi.getText().trim();
		String email =this.txtEmail.getText().trim();
		boolean gioTinh = this.rdNam.isSelected();
		KhachHang kh = new KhachHang(maKhacHang, ho, ten, sdt, diaChi, email, gioTinh);
		KhachHang_DAO khDao = new KhachHang_DAO();
		try {
			if(khDao.capNhatKhachHang(kh)) {
				loadData();
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
			}else {
				JOptionPane.showMessageDialog(this, "Chưa có khách hàng nào đc cập nhât");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new QuanLyKhachHang("Hello","Test").setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = this.table.getSelectedRow();
		String maKH = this.model.getValueAt(row, 0).toString();
		String ho = this.model.getValueAt(row, 1).toString();
		String ten = this.model.getValueAt(row, 2).toString();
		String sdt = this.model.getValueAt(row, 3).toString();
		String diaChi = this.model.getValueAt(row, 4).toString();
		String Email = this.model.getValueAt(row, 5).toString();
		String gioiTinh = this.model.getValueAt(row,6).toString();
		this.txtMa.setText(maKH);
		this.txtHo.setText(ho);
		this.txtTen.setText(ten);
		this.txtSdt.setText(sdt);
		this.txtDiaChi.setText(diaChi);
		this.txtEmail.setText(Email);
		if(gioiTinh.equalsIgnoreCase("Nam"))
			rdNam.setSelected(true);
		else
			rdNu.setSelected(true);
		this.txtMa.setEditable(false);
		this.txtHo.setEditable(false);
		this.txtTen.setEditable(false);
		this.txtSdt.setEditable(false);
		this.txtDiaChi.setEditable(false);
		this.txtEmail.setEditable(false);
		rdNam.setEnabled(false);
		rdNu.setEnabled(false);
		this.btnThem.setText("Huỷ");
		this.btnCapNhat.setText("Cập nhật");
		this.btnCapNhat.setEnabled(true);
		
		
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
