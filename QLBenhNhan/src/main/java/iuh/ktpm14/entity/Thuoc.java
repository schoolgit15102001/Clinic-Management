package iuh.ktpm14.entity;

import org.bson.types.ObjectId;

public class Thuoc {
	private ObjectId _id;
	private String ten_thuoc;
	private String huong_dan;
	
	public Thuoc() {
		// TODO Auto-generated constructor stub
	}

	public Thuoc(String ten_thuoc, String huong_dan) {
		super();
		this.ten_thuoc = ten_thuoc;
		this.huong_dan = huong_dan;
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId id) {
		this._id = id;
	}

	public String getTen_thuoc() {
		return ten_thuoc;
	}

	public void setTen_thuoc(String ten_thuoc) {
		this.ten_thuoc = ten_thuoc;
	}

	public String getHuong_dan() {
		return huong_dan;
	}

	public void setHuong_dan(String huong_dan) {
		this.huong_dan = huong_dan;
	}

	@Override
	public String toString() {
		return "Thuoc [id=" + _id.get() + ", ten_thuoc=" + ten_thuoc + ", huong_dan=" + huong_dan + "]";
	}

	
}