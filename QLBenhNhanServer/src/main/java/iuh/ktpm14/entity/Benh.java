package iuh.ktpm14.entity;

import java.io.Serializable;
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
@Table(name = "benhs")
public class Benh implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String tenBenh;
	
	@OneToMany(mappedBy = "benh", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ChiTietPhieuKham> chiTietPhieuKhams = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Benh [id=" + id + ", tenBenh=" + tenBenh + "]";
	}
}


