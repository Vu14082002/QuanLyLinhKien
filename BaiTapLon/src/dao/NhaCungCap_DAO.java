package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ConnectionDB.ConnectDB;
import Entity.KhachHang;
import Entity.NhaCungCap;

public class NhaCungCap_DAO {
	public List<NhaCungCap> getAllNhaCungCap(){
		 List<NhaCungCap>  dsNhaCC= new ArrayList<NhaCungCap>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 try {
			 Statement  stm = con.createStatement();
			 ResultSet rs = stm.executeQuery("select * from NhaCungCap");
			 while(rs.next()) {
				 String maNhaCC = rs.getString(1);
				 String ten = rs.getString(2);
				 String email = rs.getString(3);
				 String diaChi = rs.getString(4);
				 String sdt = rs.getString(5);
				 NhaCungCap ncc = new NhaCungCap(maNhaCC, ten, email, diaChi, sdt);
				 dsNhaCC.add(ncc);
			 }
		}  catch (SQLException e) {
		}
		 
		return dsNhaCC;
	}
	public List<NhaCungCap> getNhaCungCapTheoMa(String maNhaCungCap){
		 List<NhaCungCap>  dsncc= new ArrayList<NhaCungCap>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 String sql="select * from NhaCungCap where maNhaCungCap=? ";
		 try {
			 PreparedStatement  stm = con.prepareStatement(sql);
			 stm.setString(1, maNhaCungCap);
			 ResultSet rs = stm.executeQuery();
			 while(rs.next()) {
				 String maNhaCC = rs.getString(1);
				 String ten = rs.getString(2);
				 String email = rs.getString(3);
				 String diaChi = rs.getString(4);
				 String sdt = rs.getString(5);
				 NhaCungCap ncc = new NhaCungCap(maNhaCC, ten, email, diaChi, sdt);
				 dsncc.add(ncc);
			 }
		}  catch (SQLException e) {
		}
		return dsncc;
	}
	public boolean themNhaCungCap(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		int n=0;
		try {
			stm =con.prepareStatement("insert into NhaCungCap values (?, ?, ?, ?, ?)");
			stm.setString(1,ncc.getMaNhaCungCap());
			stm.setString(2,ncc.getTenNhaCungCap());
			stm.setString(3,ncc.getEmail());
			stm.setString(4,ncc.getDiaChi());
			stm.setString(5,ncc.getSdt());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean xoaNhaCungCap(String maNhaCungCap) throws SQLException{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="delete NhaCungCap where maNhaCungCap=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, maNhaCungCap);
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			stm.close();
		}
		return n > 0;
	}
	public boolean capNhatNhaCungCap(NhaCungCap ncc) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		String sql ="update NhaCungCap set tenNhaCungCap=?, "
				+ "email=?, diaChi=?, soDienThoai=? where maNhaCungCap=?";
		int n=0;
		try {
			stm =con.prepareStatement(sql);
			stm.setString(1, ncc.getTenNhaCungCap());
			stm.setString(2, ncc.getEmail());
			stm.setString(3, ncc.getDiaChi());
			stm.setString(4, ncc.getSdt());
			stm.setString(5, ncc.getMaNhaCungCap());
			n=stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stm.close();
		}
		return n > 0;
	}

}
