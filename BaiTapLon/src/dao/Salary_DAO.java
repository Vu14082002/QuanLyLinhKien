package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.PhongBanEntity;
import Entity.SalaryEntity;


public class Salary_DAO {
	public ArrayList<SalaryEntity> getSalary() throws SQLException {
		ArrayList<SalaryEntity> listNV = new ArrayList<SalaryEntity>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = null;
		try {
			String sqlString = "SELECT * FROM NhanVien";
			statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				PhongBanEntity phongBan = new PhongBanEntity(re.getString(11));
				listNV.add(new SalaryEntity(re.getString(1),re.getString(2) ,re.getString(3) , re.getBoolean(7),phongBan));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			statement.close();
		}
		return listNV;
	}
}
