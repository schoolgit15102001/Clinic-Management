package service;

import java.util.List;

import entity.ChiTietPhieuKham;

public interface ChiTietPhieuKhamService {
	public boolean createChiTietPhieuKham(ChiTietPhieuKham chiTietPhieuKham);
	public List<ChiTietPhieuKham> findAll();
	public ChiTietPhieuKham findById(String id);
	
	List<ChiTietPhieuKham> findByIdHoSo(String idHoSo);
}
