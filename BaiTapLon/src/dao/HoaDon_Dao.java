package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import ConnectionDB.ConnectDB;
import Entity.HoaDon;

public class HoaDon_Dao {
	public HoaDon_Dao() {
		
	}
	
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Statement stm  = null;
		try {
			ConnectionDB.ConnectDB.getInstance();
			Connection con = ConnectionDB.ConnectDB.getConnection();
			String sqlQuery = "select * from HoaDon";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sqlQuery);
			
			while(rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maNhanVien = rs.getString("maNhanVien");
				String maKhachHang = rs.getString("maKhachHang");
				Date ngayDatHang = rs.getDate("ngayDatHang");
				Date ngayGiaoHang = rs.getDate("ngayGiaoHang");
				Date ngayChuyen = rs.getDate("ngayChuyen");
				String noiNhanHang = rs.getString("noiNhanHang");
				
				dsHoaDon.add(new HoaDon(maHoaDon, maNhanVien, maKhachHang, ngayDatHang, ngayGiaoHang, ngayChuyen, noiNhanHang));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dsHoaDon;
		
	}
	public boolean addHoaDon(HoaDon hd) {
		PreparedStatement stm = null;
		int isInserted = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sqlQuery = "INSERT INTO HoaDon([maHoaDon], [maNhanVien], [maKhachHang], [ngayDatHang], [ngayGiaoHang], [ngayChuyen], [noiNhanHang])"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?)";
			stm = con.prepareStatement(sqlQuery);
			stm.setString(1, hd.getMaHoaDon());
			stm.setString(2, hd.getMaNhanVien());
			stm.setString(3, hd.getMaKhachHang());
			stm.setDate(4,  new java.sql.Date(hd.getNgayDatHang().getTime()));
			stm.setDate(5, new java.sql.Date(hd.getNgayGiaoHang().getTime()));
			stm.setDate(6,  new java.sql.Date(hd.getNgayGiaoHang().getTime()));
			stm.setString(7, hd.getNoiNhanHang());
			isInserted = stm.executeUpdate();
			if (isInserted == 0) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return false;
		} finally {
			try {

				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	public boolean modifieldHoaDon(HoaDon hd) {
		PreparedStatement stm = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String queryUpdate  = 
					"update [dbo].[HoaDon]"  +
							" set [maNhanVien] = ?,"
							+"[ngayDatHang] = ?,"
							+"[ngayGiaoHang] = ?,"
							+"[ngayChuyen] = ?,"
							+"[noiNhanHang] = ?"
							+" where [maHoaDon] = ?";
;
			stm = con.prepareStatement(queryUpdate);
			stm.setString(1, hd.getMaNhanVien());
			stm.setDate(2, new java.sql.Date(hd.getNgayDatHang().getTime()));
			stm.setDate(3,  new java.sql.Date(hd.getNgayGiaoHang().getTime()));
			stm.setDate(4,  new java.sql.Date(hd.getNgayChuyenHang().getTime()));
			stm.setString(5, hd.getNoiNhanHang());
			stm.setString(6, hd.getMaHoaDon());
			
			int inserted = stm.executeUpdate();
			if (inserted == 0) {
				return false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	public boolean deleteHoaDon(String maHD) {
		PreparedStatement stm = null;
		PreparedStatement stm2 = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sqlQueryChild = "delete from [dbo].[chiTietHoaDon] where [maHoaDon] = ?";
			stm = con.prepareStatement(sqlQueryChild);
			stm.setString(1, maHD);
			stm.executeUpdate();
			
			String sqlQuery = "delete from [dbo].[HoaDon] where [maHoaDon] = ?";
			stm2 = con.prepareStatement(sqlQuery);
			stm2.setString(1, maHD);
			
			stm2.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stm.close();
				stm2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
}
