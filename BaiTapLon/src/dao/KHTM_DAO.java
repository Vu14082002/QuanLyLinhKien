package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.KhachHangEntity;


public class KHTM_DAO {
	public ArrayList<KhachHangEntity> getKhachHang() {
		ArrayList<KhachHangEntity> listMH = new ArrayList<KhachHangEntity>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sqlString = "SELECT TOP 5  * FROM NhanVien ORDER BY SDT";
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				listMH.add(new KhachHangEntity(re.getString(1) , re.getString(2) , re.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMH;
	}
}
