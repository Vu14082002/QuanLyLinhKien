package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectionDB.ConnectDB;
import Entity.KhachHangEntity;
import Entity.SoTienPTCMHD;
import dao.SoTienPhaiTra_DAO;

public class HoaDon extends JFrame implements ActionListener {
	private JPanel pnBorder;
	private JPanel pnCenter;
	private JPanel pnHeader;
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private JPanel pnCenterLayout;
	private JButton btnTroLai;
	String maNhanVien;
	String tenNhanVien;
	private SoTienPhaiTra_DAO soTien_Dao = new SoTienPhaiTra_DAO();
	public HoaDon(String maNhanVien, String tenNhanVien) {
		ConnectDB.getInstance().connect();
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		setTitle("Quản lý linh kiện");
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JLabel lblTieuDe;
		pnHeader = new JPanel();
		lblTieuDe = new JLabel("Quản Lý Hóa Đơn");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);
		pnHeader.add(lblTieuDe);
		pnCenter = new JPanel();
		pnCenterLayout = new JPanel(new BorderLayout());
		pnBorder = new JPanel(new BorderLayout());
		add(pnBorder);
		pnBorder.add(pnHeader, BorderLayout.NORTH);
		tableSalary();
		pnBorder.add(pnCenter,BorderLayout.CENTER);
		btnTroLai = new JButton("Quay Lại");
		pnBorder.add(btnTroLai, BorderLayout.SOUTH);
		docDuLieu();
		btnTroLai.addActionListener(this);
	}
	public void tableSalary() {
		String[] colHeader = { "Mã Hóa Đơn" , "Ngày Lập" , "Tổng Tiền"};
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		pnCenter.add(new JScrollPane(tableNhanVien));
	}
	private void docDuLieu() {
		while (modelNhanVien.getRowCount() != 0) {
			modelNhanVien.removeRow(0);
		}
		ArrayList<SoTienPTCMHD> listMH = soTien_Dao.getKhachHang();
		for (SoTienPTCMHD matHang : listMH) {
			String data[] = { matHang.getMaHD(), matHang.getNgayDatHang() + "", matHang.getThanhTien() + "" };
			modelNhanVien.addRow(data);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTroLai)) {
			new ThongKe(this.maNhanVien, this.maNhanVien).setVisible(true);
			this.dispose();
		}
	}
}
