package GUI;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ConnectionDB.ConnectDB;
import Entity.DoanhThuEntity;
import Entity.KhachHangEntity;
import dao.DoanhThu_DAO;


public class DoanhThu extends JFrame implements ActionListener {
	private JPanel pnBorder;
	private JPanel pnCenter;
	private JPanel pnHeader;
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private JPanel pnCenterLayout;
	private JButton btnTroLai;
	DoanhThu_DAO doanhThu_Dao = new DoanhThu_DAO();
	JTextField txtTongTien;
	String maNhanVien;
	String tenNhanVien;
	public DoanhThu(String maNhanVien,String tenNhanVien) {	
		ConnectDB.getInstance().connect();
		this.maNhanVien=maNhanVien;
		this.tenNhanVien=tenNhanVien;
		setTitle("Quản lý linh kiện");
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel lblTieuDe;
		pnHeader = new JPanel();
		lblTieuDe = new JLabel("Doanh Thu");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);
		pnHeader.add(lblTieuDe);
		pnCenter = new JPanel();
		pnCenterLayout = new JPanel(new BorderLayout());
		pnBorder = new JPanel(new BorderLayout());
		add(pnBorder);
		pnBorder.add(pnHeader, BorderLayout.NORTH);
		tableSalary();
		JLabel lbTongTien = new JLabel("Tong Tien");
		txtTongTien = new JTextField(40);
		pnCenter.add(lbTongTien);
		pnCenter.add(txtTongTien);
		txtTongTien.disable();
		pnBorder.add(pnCenter,BorderLayout.CENTER);
		
		btnTroLai = new JButton("Quay Lại");
		pnBorder.add(btnTroLai, BorderLayout.SOUTH);
		docDuLieu();
		btnTroLai.addActionListener(this);
	}
	public void tableSalary() {
		String[] colHeader = { "Ngày" ,"Tháng" , "Năm" , "Tổng Doanh Thu" };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		pnCenter.add(new JScrollPane(tableNhanVien));
	}
	
	private void docDuLieu() {
		while(modelNhanVien.getRowCount() != 0) {
				modelNhanVien.removeRow(0);
		}
		ArrayList<DoanhThuEntity> listMH = doanhThu_Dao.getKhachHang();
		for (DoanhThuEntity matHang : listMH) {
			String data[] = {matHang.getMaHang() , matHang.getTenHang() , matHang.getSoLuong() + "" , matHang.getTongTien() + ""};
			modelNhanVien.addRow(data);
		}
		double tongTien = doanhThu_Dao.getTongTien();
		txtTongTien.setText(tongTien + "");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTroLai)) {
			new ThongKe(this.tenNhanVien,this.tenNhanVien).setVisible(true);
			this.dispose();
		}
	}
}
