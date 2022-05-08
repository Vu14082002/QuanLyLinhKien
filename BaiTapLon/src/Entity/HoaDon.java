package Entity;

import java.util.Date;

public class HoaDon {
	private String maHoaDon;
	private String maNhanVien;
	private String maKhachHang;
	private Date ngayDatHang;
	private Date ngayGiaoHang;
	private Date ngayChuyenHang;
	private String noiNhanHang;
	public HoaDon() {
		// TODO Auto-generated constructor stub
	}
	public HoaDon(String maHoaDon, String maNhanVien, String maKhachHang, Date ngayDatHang, Date ngayGiaoHang,
			Date ngayChuyenHang, String noiNhanHang) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhanVien = maNhanVien;
		this.maKhachHang = maKhachHang;
		this.ngayDatHang = ngayDatHang;
		this.ngayGiaoHang = ngayGiaoHang;
		this.ngayChuyenHang = ngayChuyenHang;
		this.noiNhanHang = noiNhanHang;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public Date getNgayDatHang() {
		return ngayDatHang;
	}
	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}
	public Date getNgayGiaoHang() {
		return ngayGiaoHang;
	}
	public void setNgayGiaoHang(Date ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}
	public Date getNgayChuyenHang() {
		return ngayChuyenHang;
	}
	public void setNgayChuyenHang(Date ngayChuyenHang) {
		this.ngayChuyenHang = ngayChuyenHang;
	}
	public String getNoiNhanHang() {
		return noiNhanHang;
	}
	public void setNoiNhanHang(String noiNhanHang) {
		this.noiNhanHang = noiNhanHang;
	}
	
}
