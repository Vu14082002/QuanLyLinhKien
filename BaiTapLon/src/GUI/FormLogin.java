package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ConnectionDB.ConnectDB;
import Entity.NhanVien;
import dao.NhanVien_DAO;

public class FormLogin extends JFrame implements ActionListener, MouseListener {
	private JLabel labelEmail, labelMatKhau, labelNhoMatKhau;
	private JCheckBox checkBoxRememberLogin;
	private JButton btnDangNhap;
	private JTextField txtMaNhanVien;
	private JPasswordField txtPass;
	private TrangChu trangchu;
	private NhanVien_DAO nhanVienDao;

	public FormLogin() {
		ConnectDB.getInstance().connect();
		nhanVienDao = new NhanVien_DAO();
		createFormLogin();
	}

	public void createFormLogin() {
		this.setTitle("Quản lý linh kiện");
		this.setResizable(false);
		this.setSize(800, 450);
//		this.pack();
		this.setLocationRelativeTo(null);

		JLabel labelHeader = new JLabel("LOGIn", SwingConstants.CENTER);
		labelHeader.setFont(new Font("arial", Font.BOLD, 24));
		labelHeader.setForeground(Color.BLUE);
		JPanel panelHeader = new JPanel();
		panelHeader.add(Box.createVerticalStrut(30));
		panelHeader.add(labelHeader);
		this.add(panelHeader, BorderLayout.NORTH);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		// bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));

		JLabel labelImg = new JLabel();
		labelImg.setIcon(new ImageIcon(this.getClass().getResource("/images_LinhKien/lenovo2.png")));
		labelImg.setBounds(70, 50, 250, 250);
		bodyPanel.add(labelImg);

		JPanel panelInputLogin = new JPanel();
		panelInputLogin.setLayout(new BoxLayout(panelInputLogin, BoxLayout.Y_AXIS));

		JPanel panelEmail = new JPanel();
		panelEmail.setLayout(new BoxLayout(panelEmail, BoxLayout.X_AXIS));
		panelEmail.add(labelEmail = new JLabel("MãNV: "));
		panelEmail.add(Box.createHorizontalStrut(10));
		panelEmail.add(txtMaNhanVien = new JTextField(5));
		panelEmail.setMaximumSize(new Dimension(380, 25));
		panelInputLogin.add(panelEmail);
		panelInputLogin.add(Box.createVerticalStrut(20));

		JPanel panelPassword = new JPanel();
		panelPassword.setLayout(new BoxLayout(panelPassword, BoxLayout.X_AXIS));
		panelPassword.add(labelMatKhau = new JLabel("Mật khẩu:"));
		panelPassword.add(Box.createHorizontalStrut(10));
		panelPassword.add(txtPass = new JPasswordField(5));
		panelPassword.setMaximumSize(new Dimension(380, 25));
		panelInputLogin.add(panelPassword);
		panelInputLogin.add(Box.createVerticalStrut(20));

		JPanel checkRememberPass = new JPanel();
		checkRememberPass.setLayout(new BoxLayout(checkRememberPass, BoxLayout.X_AXIS));
		JLabel labelTemp = new JLabel();
		labelTemp.setPreferredSize(labelMatKhau.getPreferredSize());
		checkRememberPass.add(labelTemp);
		checkRememberPass.add(Box.createHorizontalStrut(5));
		checkRememberPass.add(checkBoxRememberLogin = new JCheckBox("Nhớ mật khẩu"));
		checkRememberPass.add(Box.createHorizontalGlue());
		checkRememberPass.setMaximumSize(new Dimension(380, 25));

		JPanel panelDangNhap = new JPanel();
		panelDangNhap.setLayout(new BoxLayout(panelDangNhap, BoxLayout.X_AXIS));
		JLabel labelTemp2 = new JLabel();
		labelTemp2.setPreferredSize(labelTemp.getPreferredSize());
		panelDangNhap.add(labelTemp2);
		panelDangNhap.add(Box.createHorizontalStrut(9));
		panelDangNhap.add(btnDangNhap = new JButton("Login"));
		btnDangNhap.setBackground(Color.GREEN);
		panelDangNhap.add(Box.createHorizontalGlue());
		panelDangNhap.setMaximumSize(new Dimension(380, 25));

		panelInputLogin.add(checkRememberPass);
		panelInputLogin.add(Box.createVerticalStrut(20));

		panelInputLogin.add(panelDangNhap);
		panelInputLogin.add(Box.createVerticalStrut(20));

		panelInputLogin.setBounds(350, 50, 400, 400);
		bodyPanel.add(panelInputLogin);

		labelEmail.setPreferredSize(labelMatKhau.getPreferredSize());
		this.add(bodyPanel);
		btnDangNhap.addActionListener(this);

	}

	public void dangKySuKien() {
		this.btnDangNhap.addActionListener(this);
	}

	public static void main(String[] args) {
		new FormLogin().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		Object o = e.getSource();
		if (this.btnDangNhap.equals(o)) {
			String tenTk = this.txtMaNhanVien.getText();
			String mk = new String(this.txtPass.getPassword());
			tenTk = tenTk.trim();
			List<NhanVien> dsNhanVien = nhanVienDao.getAllNhanVien();
			for (NhanVien ds : dsNhanVien) {
				if (mk.equals(ds.getMatKhau()) && tenTk.equals(ds.getMaNhanVien())) {
					trangchu = new TrangChu(ds.getMaNhanVien(), ds.getHo()+" "+ds.getTen());
					this.setVisible(false);
					trangchu.setVisible(true);
					return;
				}
			}
			JOptionPane.showMessageDialog(this, "Tai khoan hoac mat khau ko dung");
		}
	}
}
