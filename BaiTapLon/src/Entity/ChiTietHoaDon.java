package Entity;

public class ChiTietHoaDon {
	private String maHoaDon;
	private String maChiTietHoaDon;
	private String maLinhKien;
	private int soLuongLinhKien;
	private double donGia;
	private double thanhTien;
	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
	}
	public ChiTietHoaDon(String maHoaDon, String maChiTietHoaDon, String maLinhKien, int soLuongLinhKien, double donGia,
			double thanhTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.maChiTietHoaDon = maChiTietHoaDon;
		this.maLinhKien = maLinhKien;
		this.soLuongLinhKien = soLuongLinhKien;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}
	public void setMaChiTietHoaDon(String maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	public String getMaLinhKien() {
		return maLinhKien;
	}
	public void setMaLinhKien(String maLinhKien) {
		this.maLinhKien = maLinhKien;
	}
	public int getSoLuongLinhKien() {
		return soLuongLinhKien;
	}
	public void setSoLuongLinhKien(int soLuongLinhKien) {
		this.soLuongLinhKien = soLuongLinhKien;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	
	
}
