package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionDB.ConnectDB;
import Entity.KhachHang;
import Entity.LinhKien;

public class KhachHang_DAO {
	public List<KhachHang> getAllKhachHang(){
		 List<KhachHang>  dsKhachHang= new ArrayList<KhachHang>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 try {
			 Statement  stm = con.createStatement();
			 ResultSet rs = stm.executeQuery("select * from KhachHang");
			 while(rs.next()) {
				 String maKhachHang = rs.getString(1);
				 String ho = rs.getString(2);
				 String ten = rs.getString(3);
				 String sdt = rs.getString(4);
				 String diaChi = rs.getString(5);
				 String email = rs.getString(6);
				 boolean gioTinh = rs.getBoolean(7);
				 KhachHang kh = new KhachHang(maKhachHang, ho, ten, sdt, diaChi, email, gioTinh);
				 dsKhachHang.add(kh);
			 }
		}  catch (SQLException e) {
		}
		 
		return dsKhachHang;
	}
	public List<KhachHang> getKhachHangTheoMa(String maKhachHang){
		 List<KhachHang>  dsKhachHang= new ArrayList<KhachHang>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 String sql="select * from KhachHang where maKhachHang=? ";
		 try {
			 PreparedStatement  stm = con.prepareStatement(sql);
			 stm.setString(1, maKhachHang);
			 ResultSet rs = stm.executeQuery();
			 while(rs.next()) {
				 String maKh = rs.getString(1);
				 String ho = rs.getString(2);
				 String ten = rs.getString(3);
				 String sdt = rs.getString(4);
				 String diaCho = rs.getString(5);
				 String email = rs.getString(6);
				 boolean gioTinh = rs.getBoolean(7);
				 KhachHang kh = new KhachHang(maKh, ho, ten, sdt, diaCho, email, gioTinh);
				 dsKhachHang.add(kh);
			 }
		}  catch (SQLException e) {
		}
		return dsKhachHang;
	}
	

	public boolean themKhachHang(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		int n=0;
		try {
			stm =con.prepareStatement("insert into KhachHang values (?, ?, ?, ?, ?, ?, ?)");
			stm.setString(1,kh.getMaKhachHang());
			stm.setString(2, kh.getHo());
			stm.setString(3, kh.getTen());
			stm.setString(4, kh.getSdt());
			stm.setString(5, kh.getDiaChi());
			stm.setString(6, kh.getEmail());
			stm.setBoolean(7, kh.isGioTinh());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaKhachHang(String maKhachHang) throws SQLException{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="delete KhachHang where maKhachHang=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, maKhachHang);
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			stm.close();
		}
		return n > 0;
	}
	public boolean capNhatKhachHang(KhachHang kh) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="update KhachHang set ho=?, "
				+ "ten=?, sdt=?, diaChi =?, email=?, gioiTinh=? where maKhachHang=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, kh.getHo());
			stm.setString(2, kh.getTen());
			stm.setString(3, kh.getSdt());
			stm.setString(4, kh.getDiaChi());
			stm.setString(5, kh.getEmail());
			stm.setBoolean(6, kh.isGioTinh());
			stm.setString(7, kh.getMaKhachHang());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stm.close();
		}
		return n > 0;
	}

}
