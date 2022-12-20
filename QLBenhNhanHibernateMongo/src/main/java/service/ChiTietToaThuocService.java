package service;

import java.util.List;

import entity.ChiTietToaThuoc;

public interface ChiTietToaThuocService {
	public boolean createChiTietToaThuoc(ChiTietToaThuoc chiTietToaThuoc);
	public List<ChiTietToaThuoc> findAll();
	public ChiTietToaThuoc findById(String id);
	
	public List<ChiTietToaThuoc> findByIdToaThuoc(String idToaThuoc);
}
