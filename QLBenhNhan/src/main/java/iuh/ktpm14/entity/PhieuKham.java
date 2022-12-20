package iuh.ktpm14.entity;

import org.bson.types.ObjectId;

public class PhieuKham {
	private ObjectId id = new ObjectId();
	private ObjectId maHoSoBA;
	
	public PhieuKham(ObjectId id, ObjectId maHoSoBA) {
		super();
		this.id = id;
		this.maHoSoBA = maHoSoBA;
	}
	
	

	public PhieuKham(ObjectId maHoSoBA) {
		super();
		this.maHoSoBA = maHoSoBA;
	}



	@Override
	public String toString() {
		return "PhieuKham [id=" + id + ", maHoSoBA=" + maHoSoBA + "]";
	}



	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public ObjectId getMaHoSoBA() {
		return maHoSoBA;
	}



	public void setMaHoSoBA(ObjectId maHoSoBA) {
		this.maHoSoBA = maHoSoBA;
	}



	public PhieuKham() {
		super();
	}
	
	
	
	
	
	
}
