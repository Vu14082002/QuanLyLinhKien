package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.MatHangEntity;
import Entity.PhongBanEntity;
import Entity.SalaryEntity;


public class MatHang_DAO {
	public ArrayList<MatHangEntity> getMatHang() {
		ArrayList<MatHangEntity> listMH = new ArrayList<MatHangEntity>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sqlString = "select * from chitiethoadon as ct join linhkien as lk on ct.maLinhKien = lk.maLinhKien where soLuong >= 2";
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				
				listMH.add(new MatHangEntity(re.getString(6) , re.getString(8) , re.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMH;
	}
}
