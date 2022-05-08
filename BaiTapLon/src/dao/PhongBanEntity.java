package dao;

public class PhongBanEntity {
	private String maPB;
	private String tenPB;
	public PhongBanEntity(String maPB, String tenPB) {
		super();
		this.maPB = maPB;
		this.tenPB = tenPB;
	}
	public PhongBanEntity(String maPB) {
		this.maPB = maPB;
	}
	public PhongBanEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaPB() {
		return maPB;
	}
	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}
	public String getTenPB() {
		return tenPB;
	}
	public void setTenPB(String tenPB) {
		this.tenPB = tenPB;
	}
}
