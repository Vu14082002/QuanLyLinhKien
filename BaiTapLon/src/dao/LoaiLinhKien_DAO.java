package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionDB.ConnectDB;
import Entity.LoaiLinhKien;

public class LoaiLinhKien_DAO {
	
	public LoaiLinhKien_DAO() {
		
	}
	public List<LoaiLinhKien> getAllLoaiLinhKien(){
		 List<LoaiLinhKien>  dsLoai= new ArrayList<LoaiLinhKien>();
		 ConnectDB.getInstance();
		 Connection con= ConnectDB.getConnection();
		 try {
			 Statement  stm = con.createStatement();
			 ResultSet rs = stm.executeQuery("select * from LoaiLinhKien");
			 while(rs.next()) {
				 String ma=rs.getString("maLoai");
				 
				 String ten=rs.getString("tenLoai");
				 LoaiLinhKien loai = new LoaiLinhKien(ma,ten);
				 dsLoai.add(loai);
			 }
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoai;
	}
	public LoaiLinhKien TimKiemTen(String ten){
		Connection con= ConnectDB.getConnection();
        LoaiLinhKien llk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LoaiLinhKien where tenLoai = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                llk = new LoaiLinhKien(rs.getString(0), rs.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return llk;
    }
    public LoaiLinhKien TimKiemMa(String ma){
    	Connection con= ConnectDB.getConnection();
        LoaiLinhKien llk = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LoaiLinhKien where maLoai = ?");
            stmt.setString(0,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                llk = new LoaiLinhKien(rs.getString(0), rs.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return llk;
    }
    public boolean addLoaiLinhKien(LoaiLinhKien llk) {
    	PreparedStatement stm = null;
		int isInserted = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sqlQuery = "INSERT INTO LoaiLinhKien([maLoai], [tenLoai])"
					+ "VALUES"
					+ "(?, ?)";
			stm = con.prepareStatement(sqlQuery);
			stm.setString(1, llk.getMaloai());
			stm.setString(2, llk.getTenLinhKien());
			isInserted = stm.executeUpdate();
			if (isInserted == 0) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return false;
		} finally {
			try {

				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
    }
    public boolean deleteLLK(String maLLK) {
    	Connection con= ConnectDB.getConnection();
    	try {
            PreparedStatement stmt = con.prepareStatement("delete from LoaiLinhKien where maLoai = ?");
            stmt.setString(1, maLLK);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateLoaiLK(LoaiLinhKien llk) {
    	PreparedStatement stm = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sqlQuery = "update LoaiLinhKien "
					+ " set tenLoai = ? "
					+ "where maLoai = ?";
			stm = con.prepareStatement(sqlQuery);
			stm.setString(1, llk.getTenLinhKien());
			int num = stm.executeUpdate();
			if (num == 0) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
    }
}
