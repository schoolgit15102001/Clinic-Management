package iuh.ktpm14.service;

import java.util.List;

import iuh.ktpm14.entity.Benh;
import iuh.ktpm14.entity.HoSoBenhAn;

public interface HoSoBenhAnService {
	
	public boolean save(HoSoBenhAn benh);
	public HoSoBenhAn findBySDT(String name);
	public List<HoSoBenhAn> findAll();
	public boolean deleteBySdt(String name);
	public boolean updateBySdt(String name, HoSoBenhAn benhAn);
	

}
