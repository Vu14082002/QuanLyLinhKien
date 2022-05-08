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
			String sqlString = "SELECT * FROM NhanVien";
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				
				listMH.add(new MatHangEntity(re.getString(1) , re.getString(2) , re.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMH;
	}
}
