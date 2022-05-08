package Entity;

public class KhachHang {
	private String maKhachHang;
	private String ho;
	private String ten;
	private String sdt;
	private String diaChi;
	private String email;
	private boolean gioTinh;
	public KhachHang() {
		// TODO Auto-generated constructor stub
	}
	public KhachHang(String maKhachHang, String ho, String ten, String sdt, String diaChi, String email,
			boolean gioTinh) {
		super();
		this.maKhachHang = maKhachHang;
		this.ho = ho;
		this.ten = ten;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.email = email;
		this.gioTinh = gioTinh;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isGioTinh() {
		return gioTinh;
	}
	public void setGioTinh(boolean gioTinh) {
		this.gioTinh = gioTinh;
	}
	
	
}
