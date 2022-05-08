package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ConnectionDB.ConnectDB;
import Entity.LinhKien;
import dao.LinhKien_DAO;

public class ThongTinChiTietLinhKien extends JFrame {
	private String maLinhKien;
	private String tenLinhKien;
	private int soLuong;
	private String diaChiHinhAnh;
	private String maLoai;
	private String maNhaCungCap;
	private double donGia;

	public ThongTinChiTietLinhKien(String maLinhKien) {
		this.maLinhKien = maLinhKien;
		ConnectDB.getInstance().connect();
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("Thông tin chi tiết linh kiện");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		LinhKien_DAO lk = new LinhKien_DAO();
		List<LinhKien> dslk = lk.getAllLinhKien();
		for (LinhKien linhKien : dslk) {
			if (linhKien.getMaLinhKien().equalsIgnoreCase(this.maLinhKien)) {
				this.tenLinhKien = linhKien.getTenLinhKien();
				this.soLuong = linhKien.getSoLuong();
				this.diaChiHinhAnh = linhKien.getDiaChiHinhAnh();
				this.maLoai = linhKien.getMaLoai();
				this.maNhaCungCap = linhKien.getMaNhaCungCap();
				this.donGia = linhKien.getDonGia();
				break;
			}
		}
		init();
	}

	public void init() {
		ImageIcon icon = new ImageIcon(this.getClass().getResource(this.diaChiHinhAnh));
		Image img = icon.getImage();
		Image scale = img.getScaledInstance(250, 270, Image.SCALE_SMOOTH);
		ImageIcon scaleIcon = new ImageIcon(scale);
		JLabel lbHinhAnh = new JLabel();
		lbHinhAnh.setIcon(scaleIcon);

		Font font = new Font("arial", Font.BOLD, 16);

		JLabel lbChiTiet = new JLabel("Thông tin chi tiết linh kiện", SwingConstants.CENTER);
		JLabel lbMaLinhKien0 = new JLabel("Mã linh kiên: ");
		JLabel lbMaLinhKien = new JLabel(this.maLinhKien);
		lbMaLinhKien0.setFont(font);
		lbMaLinhKien.setFont(font);

		JLabel lbTenLinKien0 = new JLabel("Tên linh kiện: ");
		JLabel lbTenLinKien = new JLabel(this.tenLinhKien);
		lbTenLinKien0.setFont(font);
		lbTenLinKien.setFont(font);

		JLabel lbSoLuong0 = new JLabel("Số lượng sản phẩm: ");
		JLabel lbSoLuong = new JLabel(this.soLuong + "");
		lbSoLuong0.setFont(font);
		lbSoLuong.setFont(font);

		JLabel lbMaLoai0 = new JLabel("Mã loại: ");
		JLabel lbMaLoai = new JLabel(this.maLoai);
		lbMaLoai0.setFont(font);
		lbMaLoai.setFont(font);

		JLabel lbMaNhaCungCap0 = new JLabel("Mã nhà cung cấp: ");
		JLabel lbMaNhaCungCap = new JLabel(this.maNhaCungCap);
		lbMaNhaCungCap0.setFont(font);
		lbMaNhaCungCap.setFont(font);

		JLabel lbDonGia0 = new JLabel("Đơn giá: ");
		JLabel lbDonGia = new JLabel(this.donGia + "");
		lbDonGia0.setFont(font);
		lbDonGia.setFont(font);
		
		JLabel lbDiaChiAnh0 = new JLabel("Địa chỉ hình ảnh: ");
		JLabel lbDiaChiAnh = new JLabel(this.diaChiHinhAnh + "");
		lbDonGia0.setFont(font);
		lbDonGia.setFont(font);

		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.X_AXIS));
		JPanel pnTrai = new JPanel();
		pnTrai.setLayout(new BoxLayout(pnTrai, BoxLayout.Y_AXIS));
		JPanel pnPhai = new JPanel();
		pnPhai.setLayout(new BoxLayout(pnPhai, BoxLayout.Y_AXIS));
		lbChiTiet.setFont(new Font("arial", Font.BOLD, 20));
		lbChiTiet.setForeground(Color.RED);
		// pnTrai
		pnTrai.add(lbHinhAnh);
		// pnPhai
		Dimension dimension = new Dimension(150, 25);
		JPanel pn0 = new JPanel();
		pn0.setLayout(new BoxLayout(pn0, BoxLayout.X_AXIS));
		pn0.add(lbMaLoai0);
		lbMaLoai0.setMinimumSize(dimension);
		pn0.add(lbMaLoai);

		JPanel pn1 = new JPanel();
		pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
		pn1.add(Box.createRigidArea(new Dimension(100, 25)));
		pn1.add(lbTenLinKien0);
		pn1.add(lbTenLinKien);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new BoxLayout(pn2, BoxLayout.X_AXIS));
		pn2.add(Box.createRigidArea(new Dimension(100, 25)));
		pn2.add(lbSoLuong0);
		pn2.add(lbSoLuong);
		
		JPanel pn3 = new JPanel();
		pn3.setLayout(new BoxLayout(pn3, BoxLayout.X_AXIS));
		pn3.add(Box.createRigidArea(new Dimension(100, 25)));
		pn3.add(lbMaLoai0);
		pn3.add(lbMaLoai);
		
		JPanel pn4 = new JPanel();
		pn4.setLayout(new BoxLayout(pn4, BoxLayout.X_AXIS));
		pn4.add(Box.createRigidArea(new Dimension(100, 25)));
		pn4.add(lbMaNhaCungCap0);
		pn4.add(lbMaNhaCungCap);
		
		JPanel pn5 = new JPanel();
		pn5.setLayout(new BoxLayout(pn5, BoxLayout.X_AXIS));
		pn5.add(Box.createRigidArea(new Dimension(100, 25)));
		pn5.add(lbDonGia0);
		pn5.add(lbDonGia);
		
		JPanel pn6 = new JPanel();
		pn6.setLayout(new BoxLayout(pn6, BoxLayout.X_AXIS));
		pn6.add(Box.createRigidArea(new Dimension(100, 25)));
		pn6.add(lbDiaChiAnh0);
		pn6.add(lbDiaChiAnh0);

		pnPhai.add(Box.createRigidArea(new Dimension(0, 25)));
		pnPhai.add(pn0);
		pnPhai.add(pn1);
		pnPhai.add(pn2);
		pnPhai.add(pn3);
		pnPhai.add(pn4);
		pnPhai.add(pn5);
		pnPhai.add(Box.createRigidArea(new Dimension(0, 100)));


		/// add panel
		pnMain.add(pnTrai);
		pnMain.add(pnPhai);
		this.add(lbChiTiet, BorderLayout.NORTH);
		this.add(pnMain, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new ThongTinChiTietLinhKien("LK005").setVisible(true);
	}

}
