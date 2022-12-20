package iuh.ktpm14.entity;

import org.bson.types.ObjectId;

public class ChiTietPhieuKham {
	private ObjectId id = new ObjectId();
	private ObjectId maPhieuKhamBenh;
	private ObjectId maBenh;
	private String trieuChung;
	
	
	public ChiTietPhieuKham() {
		
	}


	public ChiTietPhieuKham(ObjectId id, ObjectId maPhieuKhamBenh, ObjectId maBenh, String trieuChung) {
		super();
		this.id = id;
		this.maPhieuKhamBenh = maPhieuKhamBenh;
		this.maBenh = maBenh;
		this.trieuChung = trieuChung;
	}


	public ChiTietPhieuKham(ObjectId maPhieuKhamBenh, ObjectId maBenh, String trieuChung) {
		super();
		this.maPhieuKhamBenh = maPhieuKhamBenh;
		this.maBenh = maBenh;
		this.trieuChung = trieuChung;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public ObjectId getMaPhieuKhamBenh() {
		return maPhieuKhamBenh;
	}


	public void setMaPhieuKhamBenh(ObjectId maPhieuKhamBenh) {
		this.maPhieuKhamBenh = maPhieuKhamBenh;
	}


	public ObjectId getMaBenh() {
		return maBenh;
	}


	public void setMaBenh(ObjectId maBenh) {
		this.maBenh = maBenh;
	}


	public String getTrieuChung() {
		return trieuChung;
	}


	public void setTrieuChung(String trieuChung) {
		this.trieuChung = trieuChung;
	}
	
	
	
	
	
	
}
