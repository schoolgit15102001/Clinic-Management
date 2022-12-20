package iuh.ktpm14.entity;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class ToaThuoc {
	private ObjectId _id;
	private Date ngay_lap;
	private Benh benh;
	private PhieuKham phieuKham;
	private List<ChiTietToaThuoc> chiTietToaThuocs;
	public ToaThuoc() {
		super();
		// TODO Auto-generated constructor stub
		this.ngay_lap = new Date();
	}
	public ToaThuoc(List<ChiTietToaThuoc> chiTietToaThuocs) {
		super();
		this.ngay_lap = new Date();
		this.chiTietToaThuocs = chiTietToaThuocs;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public Date getNgay_lap() {
		return ngay_lap;
	}
	public void setNgay_lap(Date ngay_lap) {
		this.ngay_lap = ngay_lap;
	}
	public List<ChiTietToaThuoc> getChiTietToaThuocs() {
		return chiTietToaThuocs;
	}
	public void setChiTietToaThuocs(List<ChiTietToaThuoc> chiTietToaThuocs) {
		this.chiTietToaThuocs = chiTietToaThuocs;
	}
	@Override
	public String toString() {
		return "ToaThuoc [_id=" + _id.get() + ", ngay_lap=" + ngay_lap + ", chiTietToaThuocs=" + chiTietToaThuocs + "]";
	}
	public Benh getBenh() {
		return benh;
	}
	public void setBenh(Benh benh) {
		this.benh = benh;
	}
	public PhieuKham getPhieuKham() {
		return phieuKham;
	}
	public void setPhieuKham(PhieuKham phieuKham) {
		this.phieuKham = phieuKham;
	}
	
	
}
