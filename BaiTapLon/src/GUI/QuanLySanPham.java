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
		this.setTitle("Qu???n l?? linh ki???n");
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
	public void homeCenter() {
		JPanel pnMain = new JPanel();
		TitledBorder tieuDe = new TitledBorder(BorderFactory.createLineBorder(Color.RED), "S???n ph???m");
		tieuDe.setTitleFont(new Font("arial", Font.BOLD, 14));
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(pnMain);
		scroll.setBorder(tieuDe);
		this.add(scroll, BorderLayout.CENTER);
		JLabel lbma = new JLabel("M?? Linh Ki???n:");
		JLabel lbTen = new JLabel("T??n Linh Ki???n:");
		JLabel lbSoLuong = new JLabel("S??? l?????ng:");
		JLabel lbDiaChi = new JLabel("?????a ch??? h??nh ???nh:");
		JLabel lbMaNhaCC = new JLabel("M?? nh?? cung c???p:");
		JLabel lbLoai = new JLabel("M?? lo???i:");
		JLabel lbdonGia = new JLabel("????n gi??:");
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
		btnThem = new JButton("Th??m");
		btnXoa = new JButton("Xo??");
		btnCapNhat = new JButton("C???p nh???t");
		btnTim = new JButton("T??m");
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
		String[] s = { "M?? linh ki???n", "T??n", "m?? nh?? cung c???p", "M?? lo???i", "S??? l?????ng", "????n gi?? nh???p",
				" ?????a ch??? h??nh ???nh" };
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
	public void timTheoMaLoai() {
		
		String maMaLoai = JOptionPane.showInputDialog("Nh???p v??o m?? c???n t??m");
		ArrayList<LinhKien> dslk = (ArrayList<LinhKien>) linhKien_DAO.getLinhKienTheoMa(maMaLoai);
		if (dslk.size() == 0) {
			JOptionPane.showMessageDialog(null, "Kh??ng c?? m?? linh ki???n n??y trong b???ng");
			
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
		if (tenSuKien.equalsIgnoreCase("Th??m")) {
			ktInput();
		}
		if (tenSuKien.equalsIgnoreCase("Hu???")) {
			them();
			this.btnThem.setText("Th??m");
		}
		if (o.equals(btnTim)) {
			if (btnTim.getText().equalsIgnoreCase("T??m")) {
				btnTim.setText("H???y");
				timTheoMaLoai();
			} else {
				btnTim.setText("T??m");
				loadData();
				
			}
		}
		if (tenSuKien.equalsIgnoreCase("Xo??")) {
			int row = this.table.getSelectedRow();
			if (this.table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "B???n ph???i ch???n d??ng mu???n xo??");
			} else {
				if (JOptionPane.showConfirmDialog(this, "B???n x??c nh???n xo?? d??ng ???? ch???n", "X??c nh??n",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maSanPham = this.model.getValueAt(row, 0).toString();
					System.out.println(maSanPham);
					this.linhKien_DAO = new LinhKien_DAO();
					try {
						if (this.linhKien_DAO.xoaLinhKien(maSanPham)) {
							JOptionPane.showMessageDialog(this, "Xo?? th??nh c??ng");
							huy();
							loadData();
						} else {
							JOptionPane.showMessageDialog(this, "Xo?? b??? l??i", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		}
		if (tenSuKien.equalsIgnoreCase("C???p nh???t")) {
			capNhat();
			this.txtMa.setEditable(false);
			this.btnCapNhat.setText("L??u");
			this.btnCapNhat.setEnabled(true);
			this.btnThem.setEnabled(true);
			this.btnTim.setEnabled(false);
			this.btnThem.setText("Hu???");
		}
		if (tenSuKien.equalsIgnoreCase("L??u")) {
			ktUpdate();
			this.btnCapNhat.setText("C???p nh???t");
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
			new QuanLyLoaiLinhKien(maNhanVien,tenNhanVien).setVisible(true);
			this.setVisible(false);
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
		this.btnThem.setText("Hu???");
		this.btnCapNhat.setEnabled(true);
		this.btnCapNhat.setText("C???p nh???t");
		this.btnTim.setEnabled(false);
		loadData();
	}
	public void capNhat() {
		this.txtMa.setEditable(false);
		this.txtDiaChi.setEditable(true);
		this.txtTen.setEditable(true);
		this.txtDonGia.setEditable(true);
		this.txtmaLoai.setEditable(true);
		this.txtMaNhaCCC.setEditable(true);
		this.txtSoLuong.setEditable(true);
		this.btnThem.setText("Hu???");
		this.btnCapNhat.setEnabled(true);
		this.btnTim.setEnabled(false);
	}
	public void them() {
		this.btnThem.setText("Th??m");
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
		this.btnThem.setText("Hu???");
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
			JOptionPane.showMessageDialog(this, "M?? s???n ph???m kh??ng ???????c ????? tr???ng");
			this.txtMa.setRequestFocusEnabled(true);
			return false;
		}
		if (tenLinhKien.equals("")) {
			JOptionPane.showMessageDialog(this, "T??n linh ki???n kh??ng  ???????c ???? tr???ng");
			this.txtTen.setRequestFocusEnabled(true);
			return false;
		}
		if (nhaCungCap.equals("")) {
			JOptionPane.showMessageDialog(this, "M?? nh?? cung c???p kh??ng ??c ????? tr???ng");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		
		if (!maLinhKien.matches("^LK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "M?? Linh ki???n theo ?????nh d???ng: LKxxx v???i x l?? c??c s??? b???t k???");
			return false;
		}
		if (!tenLinhKien.matches("^([A-Z][a-z]+)(\\s[a-zA-Z0-9]+)*$")) {
			JOptionPane.showMessageDialog(null, "T??n linh ki???n b???t ?????u b???ng in hoa, t??? ?????u ti??n kh??ng c?? s???. c??c t??? kh??ng c?? k?? t??? ?????c bi???t");
			return false;
		}
		if (!nhaCungCap.matches("^NCC\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "M?? Nh?? cung c???p theo ?????nh d???ng: NCCxxx v???i x l?? c??c s??? b???t k???");
			return false;
		}
		if (!maLoai.matches("^LLK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "M?? Lo???i linh ki???n theo ?????nh d???ng: LLKxxx v???i x l?? c??c s??? b???t k???");
			return false;
		}
		try {
			soLuongInt = Integer.parseInt(soLuong);
			if (soLuongInt <= 0) {
				JOptionPane.showMessageDialog(null, "S??? l?????ng ph???i l???n h??n 0");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "?????u v??o c???a field S??? l?????ng kh??ng h???p l???!");
			return false;
		}
		try {
			donGiaDou = Double.parseDouble(donGia);
			if (donGiaDou < 0) {
				JOptionPane.showMessageDialog(null, "????n gi?? ph???i l???n h??n b???ng 0");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "?????u v??o c???a field ????n gi?? kh??ng h???p l???!");
			return false;
		}
	
		if (!diaChiHinhAnh.matches(".+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$")) {
			JOptionPane.showMessageDialog(null, "??u??i ?????nh d???ng ph???i l?? .png ho???c .jpg");
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
			JOptionPane.showMessageDialog(this, "Nh?? cung c???p kh??ng t???n t???i trong Database");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		if (maLoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Lo???i s???n ph???m kh??ng ??c ????? tr???ng");
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
			JOptionPane.showMessageDialog(this, "M?? lo???i kh??ng t???n t???i");
			return false;
		}

		try {
			soLuongInt = Integer.parseInt(this.txtSoLuong.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "S??? l?????ng linh ki???n b???n nh???p ko h???p l???", "L???i", JOptionPane.YES_OPTION);
			return false;
		}
		try {
			donGiaDou = Double.parseDouble(this.txtDonGia.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "????n gi??  linh ki???n b???n nh???p ko h???p l???", "L???i", JOptionPane.YES_OPTION);
			return false;
		}
		String diaChi = this.txtDiaChi.getText().trim();
		if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(this, "B???n ch??a ch???n ???nh ????? l??u");
			return false;
		}
		String diaChiNew = "/images_LinhKien/" + diaChi;
		LinhKien_DAO lk_Dao = new LinhKien_DAO();
		LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuongInt, diaChiNew, maLoai, nhaCungCap, donGiaDou);
		if (!lk_Dao.themLinhKien(lk)) {
			JOptionPane.showMessageDialog(this, "Th??m kh??ng th??nh c??ng");
			return false;
		}
		JOptionPane.showMessageDialog(this, "Th??m th??nh c??ng");
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
			JOptionPane.showMessageDialog(this, "T??n linh ki???n kh??ng  ???????c ???? tr???ng");
			this.txtTen.setRequestFocusEnabled(true);
			return false;
		}
		if (nhaCungCap.equals("")) {
			JOptionPane.showMessageDialog(this, "M?? nh?? cung c???p kh??ng ??c ????? tr???ng");
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
			JOptionPane.showMessageDialog(this, "Nh?? cung c???p kh??ng t???n t???i trong Database");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		if (maLoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Lo???i s???n ph???m kh??ng ??c ????? tr???ng");
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
			JOptionPane.showMessageDialog(this, "M?? lo???i kh??ng t???n t???i");
			return false;
		}

		try {
			soLuong = Integer.parseInt(this.txtSoLuong.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "S??? l?????ng linh ki???n b???n nh???p ko h???p l???", "L???i", JOptionPane.YES_OPTION);
			return false;
		}
		try {
			donGia = Double.parseDouble(this.txtDonGia.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "????n gi??  linh ki???n b???n nh???p ko h???p l???", "L???i", JOptionPane.YES_OPTION);
			return false;
		}
		String diaChi = this.txtDiaChi.getText().trim();
		if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(this, "B???n ch??a ch???n ???nh ????? l??u");
			return false;
		}
		LinhKien_DAO lk_Dao = new LinhKien_DAO();
		LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuong, diaChi, maLoai, nhaCungCap, donGia);
		try {
			if (!lk_Dao.capNhatLinhKien(lk)) {
				JOptionPane.showMessageDialog(this, "C???p nh???t kh??ng th??nh c??ng");
				return false;
			}
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadData();
		JOptionPane.showMessageDialog(this, "C???p nh???t  th??nh c??ng");
		return true;
	}
	public static void main(String[] args) {
		new QuanLySanPham("Hello","Test").setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		this.btnXoa.setEnabled(true);
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
			this.btnTim.setEnabled(true);

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
			if (JOptionPane.showConfirmDialog(this, "B???n x??c nh???n mu???n tho??t ch????ng tr??nh", "",
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
