package Entity;

public class LoaiLinhKien {
	private String maloai;
	private String tenLinhKien;
	public LoaiLinhKien() {
		// TODO Auto-generated constructor stub
	}
	
	public LoaiLinhKien(String maloai, String tenLinhKien) {
		super();
		this.maloai = maloai;
		this.tenLinhKien = tenLinhKien;
	}

	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}
	public String getTenLinhKien() {
		return tenLinhKien;
	}
	public void setTenLinhKien(String tenLinhKien) {
		this.tenLinhKien = tenLinhKien;
	}
	
	
}
