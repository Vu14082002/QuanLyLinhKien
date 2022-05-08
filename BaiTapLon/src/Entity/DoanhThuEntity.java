package Entity;

public class DoanhThuEntity {
	private String maHang;
	private String tenHang;
	private int soLuong;
	private double tongTien;
	public DoanhThuEntity(String maHang, String tenHang, int soLuong, double tongTien) {
		super();
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}
	public DoanhThuEntity() {
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
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
}
