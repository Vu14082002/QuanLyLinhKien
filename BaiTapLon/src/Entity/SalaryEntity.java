package Entity;

public class SalaryEntity {
	private String maNV;
	private String hoHV;
	private String tenNV;
	private boolean isGioiTinh;
	private PhongBanEntity phongBan;
	public SalaryEntity(String maNV, String hoHV, String tenNV, boolean isGioiTinh, PhongBanEntity phongBan) {
		super();
		this.maNV = maNV;
		this.hoHV = hoHV;
		this.tenNV = tenNV;
		this.isGioiTinh = isGioiTinh;
		this.phongBan = phongBan;
	}
	public SalaryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoHV() {
		return hoHV;
	}
	public void setHoHV(String hoHV) {
		this.hoHV = hoHV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public boolean isGioiTinh() {
		return isGioiTinh;
	}
	public void setGioiTinh(boolean isGioiTinh) {
		this.isGioiTinh = isGioiTinh;
	}
	public PhongBanEntity getPhongBan() {
		return phongBan;
	}
	public void setPhongBan(PhongBanEntity phongBan) {
		this.phongBan = phongBan;
	}
	
}	
