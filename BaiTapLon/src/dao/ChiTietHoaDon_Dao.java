package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.ChiTietHoaDon;

public class ChiTietHoaDon_Dao {
	public ChiTietHoaDon_Dao() {
		
	}
	
	
	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDon(){
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		Statement stm  = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sqlString = "select * from chiTietHoaDon join LinhKien on chiTietHoaDon.maLinhKien = LinhKien.maLinhKien";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sqlString);
			
			while (rs.next()) {
				String maCTHD = rs.getString("maChiTietHoaDon");
				String maHD = rs.getString("maHoaDon");
				String maLK = rs.getString("maLinhKien");
				double donGia = rs.getDouble("donGia");
				int soLuong = rs.getInt("soLuong");
				double thanhTien = rs.getDouble("thanhTien");
				ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maCTHD, maLK, soLuong, donGia, thanhTien);
				dsCTHD.add(cthd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dsCTHD;
	}
	public boolean modifieldCTHD(ChiTietHoaDon cthd) {
		PreparedStatement stm = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sqlQuery = "UPDATE chiTietHoaDon "
					+ "set soLuong = ?, "
					+ "donGia = ?, "
					+ "thanhTien = ?, "
					+ "maLinhKien = ? "
					+ "Where maChiTietHoaDon = ? and maHoaDon = ?";
			stm = con.prepareStatement(sqlQuery);
			stm.setInt(1, cthd.getSoLuongLinhKien());
			stm.setDouble(2, cthd.getDonGia());
			stm.setDouble(3, cthd.getThanhTien());
			stm.setString(4, cthd.getMaLinhKien());
			stm.setString(5, cthd.getMaChiTietHoaDon());
			stm.setString(6, cthd.getMaHoaDon());
			int num = stm.executeUpdate();
			if (num == 0) {
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
	public boolean addCTHD(ChiTietHoaDon cthd) {
		PreparedStatement stm = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sqlQuery = "INSERT INTO chiTietHoaDon VALUES(?, ?, ?, ?, ?, ?)";
			stm = con.prepareStatement(sqlQuery);
			stm.setString(1, cthd.getMaHoaDon());
			stm.setString(2, cthd.getMaChiTietHoaDon());
			stm.setInt(3, cthd.getSoLuongLinhKien());
			stm.setDouble(4, cthd.getDonGia());
			stm.setDouble(5, cthd.getThanhTien());
			stm.setString(6, cthd.getMaLinhKien());
			
			int count = stm.executeUpdate();
			if (count == 0) {
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
	public boolean deleteCTHD(String maCTHD, String maHD) {
		PreparedStatement stm = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sqlQuery = "DELETE FROM [dbo].[chiTietHoaDon] WHERE [maHoaDon] = ? AND [maChiTietHoaDon] = ?";
			stm = con.prepareStatement(sqlQuery);
			stm.setString(1, maHD);
			stm.setString(2, maCTHD);
			int row = stm.executeUpdate();
			if (row == 0) {
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
}
