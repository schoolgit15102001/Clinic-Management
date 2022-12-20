package iuh.ktpm14.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hoSoBenhAns")
public class HoSoBenhAn {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String hoTen;
	private int tuoi;
	private String diaChi;
	private String dienThoai;
	private Date ngayLap = new Date();
	
	@OneToMany(mappedBy = "hoSoBenhAn", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ChiTietPhieuKham> chiTietPhieuKhams = new ArrayList<>();
	
	
	public HoSoBenhAn() {
	}
	
	public HoSoBenhAn(String id, String hoTen, int tuoi, String diaChi, String dienThoai, Date ngayLap) {
		this.id = id;
		this.hoTen = hoTen;
		this.tuoi = tuoi;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.ngayLap = ngayLap;
	}
	
	public HoSoBenhAn(String id, String hoTen, int tuoi, String diaChi, String dienThoai) {
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
		this.ngayLap = new Date();
	}

	@Override
	public String toString() {
		return "HoSoBenhAn [id=" + id + ", hoTen=" + hoTen + ", tuoi=" + tuoi + ", diaChi=" + diaChi + ", dienThoai="
				+ dienThoai + ", ngayLap=" + ngayLap + "]";
	}
}
