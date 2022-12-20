package iuh.ktpm14.entity;

import java.io.Serializable;

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
@Table(name = "chiTietPhieuKhams")
public class ChiTietPhieuKham implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3033812342428162088L;
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String trieuChung;
	
	@ManyToOne
	private Benh benh;

	@ManyToOne
	HoSoBenhAn hoSoBenhAn;

	@Override
	public String toString() {
		return "ChiTietPhieuKham [id=" + id + ", trieuChung=" + trieuChung + "]";
	}
}
