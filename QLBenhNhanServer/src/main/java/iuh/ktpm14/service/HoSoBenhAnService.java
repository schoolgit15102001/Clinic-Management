package iuh.ktpm14.service;

import java.util.List;

import iuh.ktpm14.entity.HoSoBenhAn;



public interface HoSoBenhAnService {
	public boolean createHoSoBenhAn(HoSoBenhAn hoSoBenhAn);

	public HoSoBenhAn findByPhoneNumber(String phoneNumber);

}
