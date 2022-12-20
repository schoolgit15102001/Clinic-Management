package iuh.ktpm14.entity;


import java.sql.Date;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;

public class HoSoBenhAn {
	private ObjectId id;
	private String hoTen;
	private int tuoi;
	private String diaChi;
	private String dienThoai;
	private LocalDateTime ngayLap;
	
	
	public HoSoBenhAn() {
		this.ngayLap = LocalDateTime.now();
	}
	
	public HoSoBenhAn(ObjectId id, String hoTen, int tuoi, String diaChi, String dienThoai, LocalDateTime ngayLap) {
		this.id = id;
		this.hoTen = hoTen;
		this.tuoi = tuoi;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.ngayLap = ngayLap;
	}
	
	public HoSoBenhAn(String hoTen, int tuoi, String diaChi, String dienThoai) {
		this.hoTen = hoTen;
		this.tuoi = tuoi;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.ngayLap = LocalDateTime.now();
	}



	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public String getHoTen() {
		return hoTen;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public int getTuoi() {
		return tuoi;
	}


	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public String getDienThoai() {
		return dienThoai;
	}


	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}


	public LocalDateTime getNgayLap() {
		return ngayLap;
	}


	public void setNgayLap() {
		this.ngayLap = LocalDateTime.now();
	}


	@Override
	public String toString() {
		return "HoSoBenhAn [id=" + id + ", hoTen=" + hoTen + ", tuoi=" + tuoi + ", diaChi=" + diaChi + ", dienThoai="
				+ dienThoai + ", ngayLap=" + ngayLap + "]";
	}
	
	

}
