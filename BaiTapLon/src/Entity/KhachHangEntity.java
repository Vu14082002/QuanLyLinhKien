package Entity;

public class KhachHangEntity {
	private String maKH;
	private String tenKH;
	private int soLuongHD;
	public KhachHangEntity(String maKH, String tenKH, int soLuongHD) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soLuongHD = soLuongHD;
	}
	public KhachHangEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public int getSoLuongHD() {
		return soLuongHD;
	}
	public void setSoLuongHD(int soLuongHD) {
		this.soLuongHD = soLuongHD;
	}
	
}
