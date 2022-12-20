package service;

import java.util.List;

import entity.HoSoBenhAn;

public interface HoSoBenhAnService {
	public boolean createHoSoBenhAn(HoSoBenhAn hoSoBenhAn);
	public List<HoSoBenhAn> findAll();
	public HoSoBenhAn findById(String id);
	public HoSoBenhAn findByName(String name);
	public HoSoBenhAn findByPhoneNumber(String phoneNumber);
	public boolean updateHoSoBenhAn(HoSoBenhAn hoSoBenhAn);
	public String deleteByName(String name);
	public String deleteById(String id);

}
