package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionDB.ConnectDB;
import Entity.PhongBan;

public class PhongBan_DAO {
	public List<PhongBan> getAllPhongBan() throws SQLException {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement stm = conn.prepareStatement("select * from PhongBan");
		ResultSet rs = stm.executeQuery();
		List<PhongBan> dspb = new ArrayList<PhongBan>();
		while(rs.next()) {
			String  maPhongBan = rs.getString(1);
			String 	tenPhongBan =rs.getString(2);
			PhongBan pb = new PhongBan(maPhongBan, tenPhongBan);
			dspb.add(pb);
		}
		return dspb;
	}
}
