package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.toedter.calendar.JDateChooser;

import ConnectionDB.ConnectDB;
import Entity.ChiTietHoaDon;
import dao.ChiTietHoaDon_Dao;
import dao.LoaiLinhKien_DAO;

public class FormChiTietHoaDon extends JFrame implements ActionListener, MouseListener, MenuListener {
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
	private JLabel lbMaCTHD, lbMaHD, lbImgAvatar, lbSoLuong, lbDonGia, lbThanhTien, lbMaLinhKien;
	private JTextField txtMaCTHD, txtMaHD, txtSoLuong, txtDonGia, txtThanhTien, txtMaLinhKien;
	private JButton btnSuaAnh, btnThem, btnXoa, btnXoaTrang, btnSua, btnLuu, btnSearch;
	private JTable tableCTHD;
	private DefaultTableModel modalTable;
	private DecimalFormat df;
	private final JFileChooser fileDialog = new JFileChooser();
	private dao.ChiTietHoaDon_Dao cthd_dao;

	public FormChiTietHoaDon(String maNhanVien, String tenNhanVien) {
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		df = new DecimalFormat("#");
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		cthd_dao = new ChiTietHoaDon_Dao();
		init();
		createFormChiTietHoaDon();
		loadDataToTable();
//		
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

	public void createFormChiTietHoaDon() {
		this.setSize(1200, 800);
		this.setTitle("Quản Lý Linh Kiện");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panelHeader = new JPanel(new BorderLayout());
		JLabel labelHeader = new JLabel("Chi Tiết Hóa Đơn", SwingConstants.CENTER);
		labelHeader.setForeground(Color.red);
		labelHeader.setFont(new Font("Arial", Font.BOLD, 20));
		panelHeader.add(labelHeader, BorderLayout.NORTH);

		JPanel bodyHeader = new JPanel();
		bodyHeader.setLayout(new BoxLayout(bodyHeader, BoxLayout.Y_AXIS));

		JPanel panelHD_CT = new JPanel();
		panelHD_CT.setLayout(new BoxLayout(panelHD_CT, BoxLayout.X_AXIS));
		panelHD_CT.setMaximumSize(new Dimension(800, 25));
		panelHD_CT.add(lbMaCTHD = new JLabel("Mã Chi Tiết Hóa Đơn: "));
		panelHD_CT.add(txtMaCTHD = new JTextField(20));
		panelHD_CT.add(Box.createHorizontalStrut(15));
		panelHD_CT.add(lbMaHD = new JLabel("Mã Hóa Đơn: "));
		panelHD_CT.add(txtMaHD = new JTextField(20));

		bodyHeader.add(Box.createVerticalStrut(30));
		bodyHeader.add(panelHD_CT);
		bodyHeader.add(Box.createVerticalStrut(30));

		JPanel panelSL_DG = new JPanel();
		panelSL_DG.setLayout(new BoxLayout(panelSL_DG, BoxLayout.X_AXIS));
		panelSL_DG.add(lbMaLinhKien = new JLabel("Mã Linh Kiện: "));
		panelSL_DG.add(txtMaLinhKien = new JTextField(20));
		panelSL_DG.add(Box.createHorizontalStrut(15));
		panelSL_DG.add(lbDonGia = new JLabel("Đơn Gía: "));
		panelSL_DG.add(txtDonGia = new JTextField(20));
		panelSL_DG.setMaximumSize(new Dimension(800, 25));

		bodyHeader.add(panelSL_DG);
		bodyHeader.add(Box.createVerticalStrut(30));

		JPanel panelSL_TT = new JPanel();
		panelSL_TT.setLayout(new BoxLayout(panelSL_TT, BoxLayout.X_AXIS));
		panelSL_TT.setMaximumSize(new Dimension(800, 25));
		panelSL_TT.add(lbSoLuong = new JLabel("Số lượng: "));
		panelSL_TT.add(txtSoLuong = new JTextField(20));
		panelSL_TT.add(Box.createHorizontalStrut(15));
		panelSL_TT.add(lbThanhTien = new JLabel("Thành Tiền: "));
		panelSL_TT.add(txtThanhTien = new JTextField(20));
		panelHeader.add(bodyHeader, BorderLayout.CENTER);

		bodyHeader.add(panelSL_TT);

		lbDonGia.setPreferredSize(lbMaCTHD.getPreferredSize());
		lbMaHD.setPreferredSize(lbMaCTHD.getPreferredSize());
		lbMaLinhKien.setPreferredSize(lbMaCTHD.getPreferredSize());
		lbSoLuong.setPreferredSize(lbMaCTHD.getPreferredSize());
		lbThanhTien.setPreferredSize(lbMaCTHD.getPreferredSize());

		JPanel panelBtn = new JPanel();
		panelBtn.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		panelBtn.setMaximumSize(new Dimension(800, 70));
		panelBtn.add(btnThem = new JButton("Thêm"));
		panelBtn.add(btnSua = new JButton("Sửa"));
		panelBtn.add(btnXoa = new JButton("Xóa"));
		panelBtn.add(btnXoaTrang = new JButton("Xóa Trắng"));
		panelBtn.add(btnSearch = new JButton("Tìm Kiếm"));
		panelBtn.add(btnLuu = new JButton("Lưu"));
		bodyHeader.add(Box.createVerticalStrut(30));
		bodyHeader.add(panelBtn);

		// tool tip
		txtMaCTHD.setToolTipText("Nhập vào Mã Chi Tiết Hóa Đơn");
		txtMaHD.setToolTipText("Nhập vòa Mã Hóa Đơn");
		txtDonGia.setToolTipText("Nhập vào đơn giá của 1 sản phẩm");
		txtMaLinhKien.setToolTipText("Nhập vào Mã linh kiện");
		txtSoLuong.setToolTipText("Nhập vào Số Lượng");
		txtThanhTien.setToolTipText("Thành tiền của Chi Tiết Hoá Đơn");

		btnThem.setToolTipText("Thêm 1 Chi Tiết Hoá Đơn");
		btnLuu.setToolTipText("Lưu Thay đổi");
		btnSua.setToolTipText("Sửa thông tin");
		btnXoa.setToolTipText("Xóa 1 Chi Tiết Hoá đơn");
		btnSearch.setToolTipText("Tìm theo mã Chi Tiết Hoá Đơn");

		JPanel headerLeft = new JPanel(new BorderLayout());
		headerLeft.setLayout(new BoxLayout(headerLeft, BoxLayout.Y_AXIS));


		panelHeader.add(headerLeft, BorderLayout.WEST);

		this.add(panelHeader, BorderLayout.CENTER);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		String fieldName[] = { "Mã CTHD", "Mã Hóa Đơn", "Mã Linh Kiện", "Đơn Gía", "Số Lượng", "Thành Tiền" };
		modalTable = new DefaultTableModel(fieldName, 0);
		tableCTHD = new JTable(modalTable);
		bodyPanel.add(Box.createVerticalStrut(20));
		bodyPanel.add(new JScrollPane(tableCTHD));

		this.add(bodyPanel, BorderLayout.SOUTH);

		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnSearch.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		btnLuu.setEnabled(false);
		txtMaCTHD.setEditable(false);
		txtMaHD.setEditable(false);
		txtThanhTien.setEditable(false);
		tableCTHD.addMouseListener(this);
		
		
		// thêm sự kiện lắng nghe keyboard
		txtDonGia.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
			// khi key up thì gọi hàm tính thành tiền
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				setThanhTien();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		txtSoLuong.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				setThanhTien();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		setTxtWhenEdit(false);
	}

	public void setThanhTien() {
		try {
			DecimalFormat dftemp = new DecimalFormat("#");
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			double donGia = Double.parseDouble(txtDonGia.getText());
			txtThanhTien.setText(dftemp.format(donGia * soLuong));
		} catch (Exception e) {
			// TODO: handle exception
			txtThanhTien.setText("0");
		}
	}

	public void setTxtWhenEdit(Boolean b) {
		txtMaCTHD.setEditable(false);
		txtMaHD.setEditable(false);
		txtDonGia.setEditable(b);
		txtMaLinhKien.setEditable(b);
		txtSoLuong.setEditable(b);
	}

	public void setTextWhenAdd(Boolean b) {
		txtMaCTHD.setEditable(b);
		txtMaHD.setEditable(b);
		txtDonGia.setEditable(b);
		txtMaLinhKien.setEditable(b);
		txtSoLuong.setEditable(b);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FormChiTietHoaDon("Nguyen Van", "Tesst").setVisible(true);
	}

	public void loadDataToTable() {
		while (tableCTHD.getRowCount() != 0) {
			modalTable.removeRow(0);
		}

		ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getAllChiTietHoaDon();
		for (ChiTietHoaDon cthd : dsCTHD) {
			String data[] = { cthd.getMaChiTietHoaDon(), cthd.getMaHoaDon(), cthd.getMaLinhKien(),
					df.format(cthd.getDonGia()), cthd.getSoLuongLinhKien() + "", df.format(cthd.getThanhTien()) };
			modalTable.addRow(data);

		}
		if (tableCTHD.getRowCount() != 0) {
			tableCTHD.setRowSelectionInterval(0, 0);
			sendDataToTxt(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tableCTHD)) {
			int row = tableCTHD.getSelectedRow();
			if (row != -1) {
				sendDataToTxt(row);
			}
		}
	}

	public void sendDataToTxt(int row) {
		txtMaCTHD.setText(tableCTHD.getValueAt(row, 0).toString());
		txtMaHD.setText(tableCTHD.getValueAt(row, 1).toString());
		txtMaLinhKien.setText(tableCTHD.getValueAt(row, 2).toString());
		txtDonGia.setText(tableCTHD.getValueAt(row, 3).toString());
		txtSoLuong.setText(tableCTHD.getValueAt(row, 4).toString());
		txtThanhTien.setText(tableCTHD.getValueAt(row, 5).toString());

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
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				xoaTrangTextField();
				btnThem.setText("Hủy");
				setTextWhenAdd(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnLuu.setEnabled(true);
				btnSearch.setEnabled(false);
				
			} else {
				if (tableCTHD.getRowCount() != 0) {
					sendDataToTxt(0);
				}
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnLuu.setEnabled(false);
				btnSearch.setEnabled(true);
				setTextWhenAdd(false);
			}
		} else if (o.equals(btnSua)) {
			if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				setTxtWhenEdit(true);
				btnThem.setEnabled(false);
				btnXoa.setEnabled(false);
				btnLuu.setEnabled(true);
				btnSearch.setEnabled(false);
				btnSua.setText("Hủy");
			} else {
				setTxtWhenEdit(false);
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnLuu.setEnabled(false);
				btnSearch.setEnabled(true);
				btnSua.setText("Sửa");

			}
		} else if (o.equals(btnSearch)) {
			int flag = 0;
			String maHD = JOptionPane.showInputDialog("Nhập vào Mã Hóa Đơn");
			String maCTHD = JOptionPane.showInputDialog("Nhập vào mã Chi Tiết Hóa Đơn");
			if (maCTHD == null || maHD == null) {
				return;
			}
			for (int i = 0; i < tableCTHD.getRowCount(); i++) {
				if (tableCTHD.getValueAt(i, 0).toString().equalsIgnoreCase(maCTHD)
						&& tableCTHD.getValueAt(i, 1).toString().equalsIgnoreCase(maHD)) {
					tableCTHD.setRowSelectionInterval(i, i);
					sendDataToTxt(i);
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				JOptionPane.showMessageDialog(null, "Không có Mã Hóa đơn này trong danh sách");
			}
		} else if (o.equals(btnXoa)) {
			int rowCountSelect = tableCTHD.getSelectedRow();
			if (rowCountSelect != -1) {
				int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa");
				if (isYes == 0) {
					// do remove record
					String maCTHD = tableCTHD.getValueAt(rowCountSelect, 0).toString();
					String maHD = tableCTHD.getValueAt(rowCountSelect, 1).toString();
					boolean isDel = cthd_dao.deleteCTHD(maCTHD, maHD);
					if (isDel) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						loadDataToTable();
					} else {
						JOptionPane.showMessageDialog(null, "Xoá thất bại");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Phải chọn vào dòng cấn xóa", "Thông Báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrangTextField();
		} else if (o.equals(btnLuu)) {
			if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				String maCTHD = txtMaCTHD.getText().trim();
				String maHD = txtMaHD.getText().trim();
				String maLK = txtMaLinhKien.getText().trim();
				double donGia = 0, thanhTien = 0;
				int soLuong = 0;
				try {
					donGia = Double.parseDouble(txtDonGia.getText());
					thanhTien = Double.parseDouble(txtThanhTien.getText());
					soLuong = Integer.parseInt(txtSoLuong.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maCTHD, maLK, soLuong, donGia, thanhTien);
				boolean isAdded = cthd_dao.modifieldCTHD(cthd);
				if (isAdded) {
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
					loadDataToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Sửa thất bại");
				}
				setTxtWhenEdit(false);
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnSearch.setEnabled(true);
			}
			else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
				String maCTHD = txtMaCTHD.getText().trim();
				String maHD = txtMaHD.getText().trim();
				String maLK = txtMaLinhKien.getText().trim();
				double donGia = 0, thanhTien = 0;
				int soLuong = 0;
				try {
					donGia = Double.parseDouble(txtDonGia.getText());
					thanhTien = Double.parseDouble(txtThanhTien.getText());
					soLuong = Integer.parseInt(txtSoLuong.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maCTHD, maLK, soLuong, donGia, thanhTien);

				boolean isAdded = cthd_dao.addCTHD(cthd);
				if (isAdded) {
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					loadDataToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thất bại");
				}

				setTextWhenAdd(false);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnSearch.setEnabled(true);
			}
			else if (e.getSource().equals(this.menuLinhKien)) {
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
//				new FormChiTietHoaDon(maNhanVien,tenNhanVien).setVisible(true);
				JOptionPane.showMessageDialog(this, "Chua lam");
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "Chua lam ");
			}
			else if (e.getSource().equals(this.menuNhaCungCap)) {
				new QuanLyNhaCungCap(maNhanVien, tenNhanVien);
				this.dispose();
			}
		}
	}

	public void xoaTrangTextField() {
		txtMaCTHD.setText("");
		txtMaHD.setText("");
		txtMaLinhKien.setText("");
		txtSoLuong.setText("");
		txtDonGia.setText("");
		txtThanhTien.setText("");
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
