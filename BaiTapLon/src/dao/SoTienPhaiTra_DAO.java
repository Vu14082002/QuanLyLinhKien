package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.SoTienPTCMHD;
public class SoTienPhaiTra_DAO {
	public ArrayList<SoTienPTCMHD> getKhachHang() {
		ArrayList<SoTienPTCMHD> listMH = new ArrayList<SoTienPTCMHD>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sqlString = "select CT.maChiTietHoaDon , hd.ngayDatHang , thanhtien from chitiethoadon as CT join hoadon as hd on hd.maHoaDon = ct.maHoaDon";
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				listMH.add(new SoTienPTCMHD(re.getString(1) , re.getDate(2) , re.getDouble(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMH;
	}
}
