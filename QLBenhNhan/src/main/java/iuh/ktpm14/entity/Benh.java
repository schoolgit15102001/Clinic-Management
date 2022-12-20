package iuh.ktpm14.entity;

import java.util.Objects;

import org.bson.types.ObjectId;

public class Benh {
	
	ObjectId id = new ObjectId();
	private String tenBenh;

	public Benh() {
		
	}
	
	
	
	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public Benh(String tenBenh) {
		this.tenBenh = tenBenh;
	}
	
	
	public String getTenBenh() {
		return tenBenh;
	}

	public void setTenBenh(String tenBenh) {
		this.tenBenh = tenBenh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenBenh);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benh other = (Benh) obj;
		return Objects.equals(tenBenh, other.tenBenh);
	}


	@Override
	public String toString() {
		return "Benh [id=" + id + ", tenBenh=" + tenBenh + "]";
	}



	
	
	
	
}
