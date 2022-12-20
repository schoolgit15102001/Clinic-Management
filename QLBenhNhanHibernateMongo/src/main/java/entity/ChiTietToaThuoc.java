package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chiTietToaThuocs")
public class ChiTietToaThuoc {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private int soLuong;
	
	@ManyToOne
	private Thuoc thuoc;
	
	@ManyToOne
	private ToaThuoc toaThuoc;
	
	@Override
	public String toString() {
		return "ChiTietToaThuoc [id=" + id + ", soLuong=" + soLuong + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public Thuoc getThuoc() {
		return thuoc;
	}

	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}

	public ToaThuoc getToaThuoc() {
		return toaThuoc;
	}

	public void setToaThuoc(ToaThuoc toaThuoc) {
		this.toaThuoc = toaThuoc;
	}
	
}