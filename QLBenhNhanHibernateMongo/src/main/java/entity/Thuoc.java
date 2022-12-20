package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thuocs")
public class Thuoc {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String tenThuoc;
	private String huongDanSuDung;
	
	@OneToMany(mappedBy = "thuoc", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChiTietToaThuoc> chiTietToaThuocs = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "Thuoc [id=" + id + ", tenThuoc=" + tenThuoc + ", huongDanSuDung=" + huongDanSuDung + "]";
	}


	
}