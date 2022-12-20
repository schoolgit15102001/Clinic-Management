package service;

import java.util.List;

import entity.ToaThuoc;

public interface ToaThuocService {
	public boolean createToaThuoc(ToaThuoc toaThuoc);
	public List<ToaThuoc> findAll();
	public ToaThuoc findById(String id);
	
	public ToaThuoc findByIdPK(String idPK);
}
