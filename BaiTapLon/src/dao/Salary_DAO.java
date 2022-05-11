package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
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
		String pattern="###.00";
		DecimalFormat df=new DecimalFormat(pattern);
		try {
			String sqlString = "SELECT * FROM NhanVien";
			statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				PhongBanEntity phongBan = new PhongBanEntity(re.getString(11));
				 LocalDate currentDate = LocalDate.parse(re.getString(8));
				int ngay = currentDate.getDayOfMonth();
				int thang = currentDate.getMonthValue();
				int year = currentDate.getYear();
				double luong = ((ngay + thang + year) * 200);
				double luongThang = Double.parseDouble(df.format(luong));
				listNV.add(new SalaryEntity(re.getString(1),re.getString(2) ,re.getString(3) , re.getBoolean(7),phongBan , luongThang));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			statement.close();
		}
		return listNV;
	}
}
