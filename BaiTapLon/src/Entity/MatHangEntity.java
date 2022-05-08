package Entity;

public class MatHangEntity {
	private String maHang;
	private String tenHang;
	private int soLuong;
	public MatHangEntity(String maHang, String tenHang, int soLuong) {
		super();
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.soLuong = soLuong;
	}
	public MatHangEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaHang() {
		return maHang;
	}
	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}
	public String getTenHang() {
		return tenHang;
	}
	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
}
