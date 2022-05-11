package Entity;

import java.sql.Date;

public class SoTienPTCMHD {
	private String maHD;
	private Date ngayDatHang;
	private double thanhTien;
	public SoTienPTCMHD(String maHD, Date ngayDatHang, double thanhTien) {
		super();
		this.maHD = maHD;
		this.ngayDatHang = ngayDatHang;
		this.thanhTien = thanhTien;
	}
	public SoTienPTCMHD() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public Date getNgayDatHang() {
		return ngayDatHang;
	}
	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
}
