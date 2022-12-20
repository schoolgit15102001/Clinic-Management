package service;

import java.util.List;
import entity.Benh;

public interface BenhService {
	public boolean createBenh(Benh benh);
	public List<Benh> findAll();
	public Benh findByName(String name);
	public Benh findById(String id);
	public boolean updateBenh(Benh benhNew);
	public String deleteByName(String name);
	public String deleteById(String id);
}
