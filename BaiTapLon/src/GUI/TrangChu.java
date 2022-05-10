package GUI;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import ConnectionDB.ConnectDB;
import Entity.LinhKien;
import Entity.LoaiLinhKien;
import dao.LinhKien_DAO;
import dao.LoaiLinhKien_DAO;

public class TrangChu extends JFrame implements ActionListener, MouseListener, MenuListener,TreeSelectionListener {
	/**
	 * 
	 */
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
	private JPanel[] pnLinhKien;
	private JLabel[] lbma = new JLabel[100];
	private JLabel[] lbAnh = new JLabel[100];
	private JLabel[] lbTenLk = new JLabel[100];
	private JLabel[] lbSoLuong = new JLabel[100];

	public TrangChu(String maNhanVien, String tenNhanVien) {
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		ConnectDB.getInstance().connect();
		this.setTitle("Quản lý linh kiện");
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setCursor(Cursor.HAND_CURSOR);
		init();
		homeWest();
		linhKien();
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

	public void homeWest() {
		rootNode = new DefaultMutableTreeNode("Linh kiện");
		tree = new JTree(rootNode);
		List<LoaiLinhKien> ds = this.loaiLinhKien.getAllLoaiLinhKien();
		for (LoaiLinhKien loai : ds) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(loai.getTenLinhKien());
			rootNode.add(node);
		}
		tree.setShowsRootHandles(true);
		JScrollPane scrol = new JScrollPane(tree);
		TitledBorder tieuDe = new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh sách linh kiện");
		tieuDe.setTitleFont(new Font("arial", Font.BOLD, 14));
		scrol.setBorder(tieuDe);
		scrol.setPreferredSize(new Dimension(200, 500));
		scrol.setBackground(Color.pink);
		this.add(scrol, BorderLayout.WEST);
		this.tree.addTreeSelectionListener(this);

	}
	public void linhKien() {
		LinhKien_DAO linhKienDao = new LinhKien_DAO();
		List<LinhKien> dsLinhKien = linhKienDao.getAllLinhKien();
		int soHang = dsLinhKien.size() / 4;
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new GridLayout(soHang , 2));
		pnLinhKien = new JPanel[100];
		int i = 0;
		for (LinhKien lk : dsLinhKien) {
			pnLinhKien[i] = new JPanel();
			pnLinhKien[i].setLayout(new FlowLayout());
			lbAnh[i] = new JLabel(new ImageIcon(this.getClass().getResource(lk.getDiaChiHinhAnh())));
			lbma[i] = new JLabel("Mã sản phâm: " + lk.getMaLinhKien());
			lbTenLk[i] = new JLabel("Tên sản phâm: " + lk.getTenLinhKien());
			lbSoLuong[i] = new JLabel("Số lượng tồn: " + lk.getSoLuong() + "");
			pnLinhKien[i].setPreferredSize(new Dimension(200, 200));
			lbAnh[i].setPreferredSize(new Dimension(150, 100));
			pnLinhKien[i].add(lbAnh[i]);
			pnLinhKien[i].add(lbma[i]);
			pnLinhKien[i].add(lbTenLk[i]);
			pnLinhKien[i].add(lbSoLuong[i]);
			pnLinhKien[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));
			lbAnh[i].addMouseListener(this);
			pnMain.add(pnLinhKien[i]);
			i++;
		}
		JScrollPane scrollPanLinhKien = new JScrollPane(pnMain);
		this.add(scrollPanLinhKien, BorderLayout.CENTER);
	}

	public void linhKienTheoLoaiSP(String maLoai) {
		LinhKien_DAO linhKienDao = new LinhKien_DAO();
		List<LinhKien> dsLinhKien = linhKienDao.getLinhKienTheoMaSP(maLoai);
		System.out.println(dsLinhKien.size());
		int soHang = dsLinhKien.size() / 4;
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new GridLayout(soHang / 4, 4));
		pnLinhKien = new JPanel[100];
		int i = 0;
		for (LinhKien lk : dsLinhKien) {
			pnLinhKien[i] = new JPanel();
			pnLinhKien[i].setLayout(new FlowLayout());
			lbAnh[i] = new JLabel(new ImageIcon(this.getClass().getResource(lk.getDiaChiHinhAnh())));
			lbma[i] = new JLabel("Mã sản phâm: " + lk.getMaLinhKien());
			lbTenLk[i] = new JLabel("Tên sản phâm: " + lk.getTenLinhKien());
			lbSoLuong[i] = new JLabel("Số lượng tồn: " + lk.getSoLuong() + "");
			pnLinhKien[i].setPreferredSize(new Dimension(200, 200));
			lbAnh[i].setPreferredSize(new Dimension(150, 100));
			pnLinhKien[i].add(lbAnh[i]);
			pnLinhKien[i].add(lbma[i]);
			pnLinhKien[i].add(lbTenLk[i]);
			pnLinhKien[i].add(lbSoLuong[i]);
			pnLinhKien[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));
			lbAnh[i].addMouseListener(this);
			pnMain.add(pnLinhKien[i]);
			i++;

		}
		JScrollPane scrollPanLinhKien = new JScrollPane(pnMain);
		this.add(scrollPanLinhKien, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws SQLException {
		new TrangChu("AD123456", "Nguyen Van Test").setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// phan nay cua menu
		Object o = e.getSource();
		if (e.getSource().equals(this.menuLinhKien)) {
			new QuanLySanPham(this.maNhanVien, this.tenNhanVien).setVisible(true);
			this.setVisible(false);
		}
		else if (e.getSource().equals(this.menuNhanVien)) {
			try {
				new QuanLyNhanVien(this.maNhanVien, this.tenNhanVien).setVisible(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		else if (e.getSource().equals(this.menuKhachHang)) {
			new QuanLyKhachHang(this.maNhanVien,this.tenNhanVien).setVisible(true);
			this.setVisible(false);
		}
		else if (e.getSource().equals(this.menuHoaDon)) {
			new FormHoaDon(this.maNhanVien,this.tenNhanVien).setVisible(true);
			this.setVisible(false);
		}
		else if (e.getSource().equals(this.menuQuanLyChiTietHoaDon)) {
			new FormChiTietHoaDon(maNhanVien, tenNhanVien).setVisible(true);
			this.setVisible(false);
		}
		else if (e.getSource().equals(this.menuLoaiLinhKien)) {
//			new FormChiTietHoaDon(maNhanVien,tenNhanVien).setVisible(true);
//			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Chua lam ");
		}
		else if (e.getSource().equals(this.menuNhaCungCap)) {
			new QuanLyNhaCungCap(maNhanVien, tenNhanVien);
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < lbAnh.length; i++) {
			if (lbAnh[i] == null) {
				return;
			}
			if (lbAnh[i].equals(e.getSource())) {
//				JOptionPane.showMessageDialog(this, lbma[i].getText());
				String[] maLk =lbma[i].getText().split(": ");
				System.out.println(maLk[1]);
				if(maLk[1]!=null)
					new ThongTinChiTietLinhKien(maLk[1]).setVisible(true);
			}
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

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Object treeEvent = this.tree.getLastSelectedPathComponent();
		DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode) treeEvent;
		String ketQua = nodeSelected.getUserObject().toString();
		List<LoaiLinhKien> ds = this.loaiLinhKien.getAllLoaiLinhKien();
		for (LoaiLinhKien loai : ds) {
			if (loai.getTenLinhKien().equalsIgnoreCase(ketQua)) {
				new TrangChuTheoSanPham(this.maNhanVien, this.tenNhanVien, loai.getMaloai()).setVisible(true);
				this.dispose();
			}
		}
		
		
	}

}
