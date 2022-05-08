package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ConnectionDB.ConnectDB;
import Entity.LinhKien;
import Entity.NhanVien;

public class NhanVien_DAO {
	PreparedStatement stm = null;
	Connection conn = null;
	ResultSet rs = null;

	public List<NhanVien> getAllNhanVien(){
		List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		conn = ConnectDB.getConnection();
		try {
			stm = conn.prepareStatement("select * from NhanVien");
			rs =stm.executeQuery();
			while (rs.next()) {
				 String maNhanVien =rs.getString("maNhanVien");
				 String ho = rs.getString("ho");
				 String ten= rs.getString("ten");
				 String sdt= rs.getString("sdt");
				 String diaChi= rs.getString("diaChi");
				 String email=rs.getString("email");
				 boolean gioiTinh =rs.getBoolean("gioiTinh");
				 Date ngayVaoLam=rs.getDate("ngayVaoLam");;
				 String soCCC=rs.getString("soCCCD");
				 String matKhau=rs.getString("matKhau");;
				 String maPhongBan=rs.getString("maPhongBan");
				 NhanVien nv = new NhanVien(maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, 
						 ngayVaoLam, soCCC, matKhau, maPhongBan);
				 dsNhanVien.add(nv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	public boolean themNhanVien(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="insert into NhanVien values\r\n"
				+ "(?,?,?,?,?,?,?,?,\r\n"
				+ "?,?,?)";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, nhanVien.getMaNhanVien());
			stm.setString(2, nhanVien.getHo());
			stm.setString(3, nhanVien.getTen());
			stm.setString(4, nhanVien.getSdt());
			stm.setString(5, nhanVien.getDiaChi());
			stm.setString(6, nhanVien.getEmail());
			stm.setBoolean(7, nhanVien.isGioiTinh());
			stm.setDate(8, new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
			stm.setString(9, nhanVien.getSoCCCD());
			stm.setString(10, nhanVien.getMatKhau());
			stm.setString(11, nhanVien.getMaPhongBan());
			stm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	public boolean capNhatNhanVien(NhanVien nhanVien) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="update NhanVien \r\n"
				+ "set ho= ?, ten= ?, sdt= ?, diaChi= ?, "
				+ "email=?, gioiTinh=?,ngayVaoLam=?, "
				+ "soCCCD=?,matKhau=?,maPhongBan=?\r\n"
				+ "where maNhanVien=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, nhanVien.getHo());
			stm.setString(2, nhanVien.getTen());
			stm.setString(3, nhanVien.getSdt());
			stm.setString(4, nhanVien.getDiaChi());
			stm.setString(5, nhanVien.getEmail());
			stm.setBoolean(6, nhanVien.isGioiTinh());
			stm.setDate(7,  new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
			stm.setString(8, nhanVien.getSoCCCD());
			stm.setString(9, nhanVien.getMatKhau());
			stm.setString(10, nhanVien.getMaPhongBan());
			stm.setString(11, nhanVien.getMaNhanVien());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stm.close();
		}
		return n > 0;
	}
	public boolean xoaNhanVien(String maNhanVien) throws SQLException{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="delete NhanVien where maNhanVien=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, maNhanVien);
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			stm.close();
		}
		return n > 0;
	}
	public NhanVien getNhanVien(String maNV){
		NhanVien nv = null;
		try {
			PreparedStatement stm = null;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			stm = con.prepareStatement("select * from NhanVien where maNhanVien = ?");
			stm.setString(1, maNV);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				 String maNhanVien =rs.getString("maNhanVien");
				 String ho = rs.getString("ho");
				 String ten= rs.getString("ten");
				 String sdt= rs.getString("sdt");
				 String diaChi= rs.getString("diaChi");
				 String email=rs.getString("email");
				 boolean gioiTinh =rs.getBoolean("gioiTinh");
				 Date ngayVaoLam=rs.getDate("ngayVaoLam");;
				 String soCCC=rs.getString("soCCCD");
				 String matKhau=rs.getString("matKhau");;
				 String maPhongBan=rs.getString("maPhongBan");
				 nv = new NhanVien(maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, 
						 ngayVaoLam, soCCC, matKhau, maPhongBan);
				 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
	}

}
