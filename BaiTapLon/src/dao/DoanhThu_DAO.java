package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.DoanhThuEntity;
import Entity.KhachHangEntity;

public class DoanhThu_DAO {
	public ArrayList<DoanhThuEntity> getKhachHang(String thang) throws SQLException {
		ArrayList<DoanhThuEntity> listMH = new ArrayList<DoanhThuEntity>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sqlString = "SELECT maLinhKien , ct.maHoaDon , soLuong,thanhTien  FROM [dbo].[chiTietHoaDon] as CT join HoaDon as HD ON CT.MAHOADON = HD.maHoaDon WHERE mONTH(NGAYDATHANG) = ?";
			statement = con.prepareStatement(sqlString);
			statement.setString(1,thang);
			ResultSet re = statement.executeQuery();
			while(re.next()) {
				listMH.add(new DoanhThuEntity(re.getString(1) , re.getString(2) , re.getInt(3),re.getDouble(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			statement.close();
		}
		return listMH;
	}
	public double getTongTien(String thang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		double tongTien = 0;
		try {
			String sqlString = "SELECT total = sum(thanhtien) FROM chitiethoadon as ct join hoadon as hd on ct.maHoaDon = hd.maHoaDon where Month(hd.ngaydathang) = ? ";
			PreparedStatement statement = con.prepareStatement(sqlString);
			statement.setString(1, thang);
			ResultSet re = statement.executeQuery();
			
			while(re.next()) {
				tongTien = re.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongTien;
	}
}
