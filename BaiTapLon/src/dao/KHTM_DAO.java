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
			String sqlString = "select hd.maKhachHang , fullname = ho + ' ' + ten , soLuong from hoadon as hd join khachhang as kh on hd.maKhachHang = kh.maKhachHang join chitiethoadon as ct on hd.maHoaDon = ct.maHoaDon where soLuong >= 2";
			Statement statement = con.createStatement();
			ResultSet re = statement.executeQuery(sqlString);
			while(re.next()) {
				listMH.add(new KhachHangEntity(re.getString(1) , re.getString(2) , re.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMH;
	}
}
