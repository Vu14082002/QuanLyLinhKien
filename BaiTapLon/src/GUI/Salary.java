package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ConnectionDB.ConnectDB;
import Entity.SalaryEntity;
import dao.Salary_DAO;

public class Salary extends JFrame implements ActionListener {
	private JPanel pnBorder;
	private JPanel pnCenter;
	private JPanel pnHeader;
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private JPanel pnCenterLayout;
	private JButton btnTroLai;
	private Salary_DAO nv_Dao = new Salary_DAO();
	private String maNhanVien;
	private String tenNhanVien;

	public Salary(String maNhanVien, String tenNhanVien) throws SQLException {
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		ConnectDB.getInstance().connect();
		setTitle("Quản lý sản phẩm");
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JLabel lblTieuDe;
		pnHeader = new JPanel();
		lblTieuDe = new JLabel("Thống Kê Lương Nhân Viên");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);
		pnHeader.add(lblTieuDe);
		pnCenter = new JPanel();
		pnCenterLayout = new JPanel(new BorderLayout());
		pnBorder = new JPanel(new BorderLayout());
		add(pnBorder);
		pnBorder.add(pnHeader, BorderLayout.NORTH);
		tableSalary();
		pnBorder.add(pnCenter, BorderLayout.CENTER);
		btnTroLai = new JButton("Quay Lại");
		pnBorder.add(btnTroLai, BorderLayout.SOUTH);
		docDuLieu();
		btnTroLai.addActionListener(this);
	}

	public void tableSalary() {
		String[] colHeader = { "Mã NV", "Họ NV", "Tên NV", "Phái", "Phòng ban","Lương" };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		pnCenter.add(new JScrollPane(tableNhanVien));
	}

	private void docDuLieu() throws SQLException {

		while (modelNhanVien.getRowCount() != 0) {
			modelNhanVien.removeRow(0);
		}
		String gioiTinh = "";
		ArrayList<SalaryEntity> listNV = nv_Dao.getSalary();
		for (SalaryEntity nhanVien : listNV) {
			if (nhanVien.isGioiTinh() == true) {
				gioiTinh = "Nam";
			} else {
				gioiTinh = "Nu";
			}
			String data[] = { nhanVien.getMaNV(), nhanVien.getHoHV(), nhanVien.getTenNV(), gioiTinh + "",
					nhanVien.getPhongBan().getMaPB() , nhanVien.getLuong() + "" };
			modelNhanVien.addRow(data);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTroLai)) {
			new ThongKe(this.maNhanVien,this.tenNhanVien).setVisible(true);
			this.dispose();
			ConnectDB.getInstance().disconnect();
		}
	}
}
