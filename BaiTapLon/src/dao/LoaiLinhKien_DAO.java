package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ConnectionDB.ConnectDB;
import Entity.LoaiLinhKien;
public class LoaiLinhKien_DAO {
	public List<LoaiLinhKien> getAllLoaiLinhKien(){
		 List<LoaiLinhKien>  dsLoai= new ArrayList<LoaiLinhKien>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 try {
			 Statement  stm = con.createStatement();
			 ResultSet rs = stm.executeQuery("select * from LoaiLinhKien");
			 while(rs.next()) {
				 LoaiLinhKien loai = new LoaiLinhKien(rs.getString(1),rs.getString(2));
				 dsLoai.add(loai);
			 }
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoai;
	}
}
