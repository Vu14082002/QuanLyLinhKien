package Entity;

public class NhaCungCap {
	private String maNhaCungCap;
	private String tenNhaCungCap;
	private String email;
	private String diaChi;
	private String sdt;
	public NhaCungCap() {
		// TODO Auto-generated constructor stub
	}
	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String email, String diaChi, String sdt) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.email = email;
		this.diaChi = diaChi;
		this.sdt = sdt;
	}
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
}
