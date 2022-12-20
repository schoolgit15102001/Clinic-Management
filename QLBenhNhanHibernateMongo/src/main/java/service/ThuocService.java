package service;

import java.util.List;

import entity.Thuoc;

public interface ThuocService {
	public boolean createThuoc(Thuoc thuoc);
	public List<Thuoc> findAll();
	public Thuoc findByName(String name);
	public Thuoc findById(String id);
	public boolean updateThuoc(Thuoc thuoc);
	public String deleteByName(String name);
	public String deleteById(String id);
}
