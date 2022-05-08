package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.DoanhThuEntity;
import Entity.KhachHangEntity;

public class DoanhThu_DAO {
	public ArrayList<DoanhThuEntity> getKhachHang() {
		ArrayList<DoanhThuEntity> listMH = new ArrayList<DoanhThuEntity>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sqlString = "SELECT maLinhKien , tenLinhKien , soLuongTon, total = soLuongTon * donGia FROM linhkien";
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				listMH.add(new DoanhThuEntity(re.getString(1) , re.getString(2) , re.getInt(3),re.getDouble(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		return listMH;
	}
	public double getTongTien() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		double tongTien = 0;
		try {
			String sqlString = "SELECT total = sum( soLuongTon * donGia) FROM linhkien";
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				tongTien = re.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongTien;
	}
}
