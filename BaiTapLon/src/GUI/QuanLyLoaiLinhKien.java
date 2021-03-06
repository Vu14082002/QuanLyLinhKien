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
		this.setTitle("Qu???n L?? Linh Ki???n");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout());
		JPanel pnNorth1 = new JPanel();
		JLabel lblTieuDe;
		lblTieuDe = new JLabel("TH??NG TIN LO???I LINH KI???N");
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

		b1.add(lblMa = new JLabel("M?? lo???i linh ki???n: "));
		b1.add(txtMa = new JTextField());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblTen = new JLabel("T??n lo???i linh ki???n:    "));
		b1.add(txtTen = new JTextField());
		b.add(Box.createVerticalStrut(10));

		TitledBorder tileTacVu = new TitledBorder("T??c v???");
		JPanel pnSouth1 = new JPanel();
		pnSouth1.setPreferredSize(new Dimension(1030, 70));
		pnSouth1.setBorder(tileTacVu);

		pnSouth1.add(bttTim = new JButton("T??m"));
		pnSouth1.add(bttThem = new JButton("Th??m"));
		pnSouth1.add(bttSua = new JButton("S???a"));
		pnSouth1.add(bttXoa = new JButton("X??a"));
		pnSouth1.add(bttXoaTrang = new JButton("X??a tr???ng"));
		pnSouth1.add(bttLuu = new JButton("L??u"));
		pnSouth1.add(bttThoat = new JButton("Tho??t"));

		lblMa.setPreferredSize(lblTen.getPreferredSize());

		pnCenter1.add(b);
		pnNorth.add(pnCenter1, BorderLayout.CENTER);
		pnNorth.add(pnSouth1, BorderLayout.SOUTH);

		TitledBorder tileDanhSach = new TitledBorder("Danh s??ch lo???i linh ki???n");
		JPanel pnCenter = new JPanel();
		pnCenter.setBorder(tileDanhSach);
		dao.LoaiLinhKien_DAO llkDao = new dao.LoaiLinhKien_DAO();
		String[] headers = { "M?? Lo???i Linh Ki???n", "T??n Lo???i Linh Ki???n" };
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

	public static void main(String[] args) {
		new QuanLyLoaiLinhKien("Nguyen Van", "Tesst").setVisible(true);
	}

	public boolean validator() {
		String maLLK = txtMa.getText().trim();
		String tenLLK = txtTen.getText().trim();
		if (maLLK.isEmpty() || tenLLK.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Kh??ng field n??o ???????c ????? tr???ng!", "L???i", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!maLLK.matches("^LLK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "M?? lo???i linh ki???n b???t ?????u b???ng LLK v?? theo sau l?? 3 ch??? s???!", "L???i", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
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
			if (bttThem.getText().equalsIgnoreCase("Th??m")) {
				bttThem.setText("H???y");
				bttSua.setEnabled(false);
				bttLuu.setEnabled(true);
				bttXoa.setEnabled(false);
				bttTim.setEnabled(false);
				setWhenAddData(true);
			} else if (bttThem.getText().equalsIgnoreCase("H???y")) {
				bttThem.setText("Th??m");
				bttSua.setEnabled(true);
				bttLuu.setEnabled(false);
				bttXoa.setEnabled(true);
				bttTim.setEnabled(true);
				setWhenAddData(false);
			}
		} else if (o.equals(bttSua)) {
			if (bttSua.getText().equalsIgnoreCase("S???a")) {
				setWhenEditField(true);
				bttSua.setText("H???y");
				bttThem.setEnabled(false);
				bttLuu.setEnabled(true);
				bttXoa.setEnabled(false);
				bttTim.setEnabled(false);
			} else if (bttSua.getText().equalsIgnoreCase("H???y")) {
				bttSua.setText("S???a");
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
				JOptionPane.showMessageDialog(this, "B???n c???n ch???n d??ng mu???n xo??");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "B???n x??c nh???n xo?? d??ng ???? ch???n, v?? c?? th??? l??m m???t m???t d??? li???u",
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
					JOptionPane.showMessageDialog(this, "Xo?? th??nh c??ng");
				} else {
					JOptionPane.showMessageDialog(this, "Xo?? kh??ng th??nh c??ng");
				}
			}
		} else if (o.equals(bttLuu)) {
			if (bttThem.getText().equalsIgnoreCase("H???y")) {
				if (!validator()) {
					return;
				}
				String ma = txtMa.getText();
				String ten = txtTen.getText();
				LoaiLinhKien llk = new LoaiLinhKien(ma, ten);
				boolean isAdded = llk_dao.addLoaiLinhKien(llk);
				if (!isAdded) {
					JOptionPane.showMessageDialog(null, "Th??m v??o th???t b???i", "Th??ng b??o",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Th??m v??o th??nh c??ng", "Th??ng b??o",
							JOptionPane.INFORMATION_MESSAGE);
					loadLLKToTable();
				}
				bttThem.setText("Th??m");
				bttXoa.setEnabled(true);
				bttSua.setEnabled(true);
				bttTim.setEnabled(true);
			} else if (bttSua.getText().equalsIgnoreCase("H???y")) {
				String maLLK = txtMa.getText();
				String tenLLK = txtTen.getText();
				LoaiLinhKien loaiLK = new LoaiLinhKien(maLLK, tenLLK);
				boolean isModifield = llk_dao.updateLoaiLK(loaiLK);
				if (isModifield) {
					JOptionPane.showMessageDialog(null, "S???a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					loadLLKToTable();
				} else {
					JOptionPane.showMessageDialog(null, "S???a th???t b???i", "Th???ng b??o", JOptionPane.ERROR_MESSAGE);
				}
				bttSua.setText("S???a");
				bttThem.setEnabled(true);
				bttXoa.setEnabled(true);
				bttTim.setEnabled(true);
				setWhenEditField(false);
			}
		} else if (o.equals(bttTim)) {
			int flag = 0;
			String ma = JOptionPane.showInputDialog(null, "Nh???p v??o m?? lo???i linh ki???n c???n t??m");
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
				JOptionPane.showMessageDialog(null, "Kh??ng c?? m?? lo???i linh ki???n n??y trong danh s??ch");
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
