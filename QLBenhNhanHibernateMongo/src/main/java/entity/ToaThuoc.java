package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "toaThuocs")
public class ToaThuoc {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Date ngayLap = new Date();
	
	@ManyToOne
	private ChiTietPhieuKham chiTietPhieuKham;
	
	@OneToMany(mappedBy = "toaThuoc", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChiTietToaThuoc> chiTietToaThuocs = new ArrayList<>();

	@Override
	public String toString() {
		return "ToaThuoc [id=" + id + ", ngayLap=" + ngayLap + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public ChiTietPhieuKham getChiTietPhieuKham() {
		return chiTietPhieuKham;
	}

	public void setChiTietPhieuKham(ChiTietPhieuKham chiTietPhieuKham) {
		this.chiTietPhieuKham = chiTietPhieuKham;
	}

	public List<ChiTietToaThuoc> getChiTietToaThuocs() {
		return chiTietToaThuocs;
	}

	public void setChiTietToaThuocs(List<ChiTietToaThuoc> chiTietToaThuocs) {
		this.chiTietToaThuocs = chiTietToaThuocs;
	}
	
}
