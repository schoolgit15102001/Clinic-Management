package iuh.ktpm14.service;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import iuh.ktpm14.entity.Benh;

public interface BenhService {
	public boolean save(Benh benh);
	public List<Benh> findAll();
	public Benh findByName(String name);
	
	public Benh findById(ObjectId name);
	
	public boolean deleteByName(String name);
	public boolean updateByName(String name, String key, String value);
}
