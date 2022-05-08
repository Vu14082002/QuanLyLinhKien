package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	public HoaDon(String maNhanVien, String tenNhanVien) {
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
		pnBorder.add(pnCenter,BorderLayout.CENTER);
		btnTroLai = new JButton("Quay Lại");
		pnBorder.add(btnTroLai, BorderLayout.SOUTH);
		
		btnTroLai.addActionListener(this);
	}
	public void tableSalary() {
		String[] colHeader = { "Mã Hóa Đơn" , "Ngày Lập" , "Tổng Tiền"};
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		pnCenter.add(new JScrollPane(tableNhanVien));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTroLai)) {
//			new ThongKe().setVisible(true);
			this.dispose();
		}
	}
}
