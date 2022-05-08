package Entity;

public class LinhKien {
	private String maLinhKien;
	private String tenLinhKien;
	private int soLuong;
	private String diaChiHinhAnh;
	private String maLoai;
	private String maNhaCungCap;
	private double donGia;
	public LinhKien() {
		// TODO Auto-generated constructor stub
	}
	public LinhKien(String maLinhKien, String tenLinhKien, int soLuong, String diaChiHinhAnh, String maLoai,
			String maNhaCungCap, double donGia) {
		super();
		this.maLinhKien = maLinhKien;
		this.tenLinhKien = tenLinhKien;
		this.soLuong = soLuong;
		this.diaChiHinhAnh = diaChiHinhAnh;
		this.maLoai = maLoai;
		this.maNhaCungCap = maNhaCungCap;
		this.donGia = donGia;
	}
	public String getMaLinhKien() {
		return maLinhKien;
	}
	public void setMaLinhKien(String maLinhKien) {
		this.maLinhKien = maLinhKien;
	}
	public String getTenLinhKien() {
		return tenLinhKien;
	}
	public void setTenLinhKien(String tenLinhKien) {
		this.tenLinhKien = tenLinhKien;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getDiaChiHinhAnh() {
		return diaChiHinhAnh;
	}
	public void setDiaChiHinhAnh(String diaChiHinhAnh) {
		this.diaChiHinhAnh = diaChiHinhAnh;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
}
