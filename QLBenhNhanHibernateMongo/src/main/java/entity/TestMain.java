package entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.Transaction;
import org.hibernate.hql.ast.origin.hql.parse.HQLParser.negatedExpression_return;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import constant.CrudStatus;
import service.impl.BenhServiceImpl;
import service.impl.ChiTietPhieuKhamImpl;
import service.impl.ChiTietToaThuocImpl;
import service.impl.HoSoBenhAnImpl;
import service.impl.ThuocImpl;
import service.impl.ToaThuocImpl;
import util.Connect;


public class TestMain {
	public static void main(String[] args) {
		//generate data test
		HoSoBenhAnImpl hoSoBenhAnImpl = new HoSoBenhAnImpl();
		ThuocImpl thuocImpl = new ThuocImpl();
		BenhServiceImpl benhServiceImpl = new BenhServiceImpl();
		ToaThuocImpl thuocToaThuocImpl = new ToaThuocImpl();
		ChiTietToaThuocImpl chiTietToaThuocImpl = new ChiTietToaThuocImpl();
		ChiTietPhieuKhamImpl chiTietPhieuKhamImpl = new ChiTietPhieuKhamImpl();
		
		for(int i = 1; i < 6; i ++) {
			HoSoBenhAn hoSoBenhAn = new HoSoBenhAn();
			hoSoBenhAn.setDiaChi("DiaChi" + i);
			hoSoBenhAn.setDienThoai("0000000" + i);
			hoSoBenhAn.setHoTen("BenhNhan" + i);
			hoSoBenhAn.setTuoi(20 + i);
			hoSoBenhAnImpl.createHoSoBenhAn(hoSoBenhAn);
			
			Thuoc thuoc = new Thuoc();
			thuoc.setTenThuoc("Thuoc " + i);
			thuoc.setHuongDanSuDung("Huong Dan " + i);
			thuocImpl.createThuoc(thuoc);
			
			Benh benh = new Benh();
			benh.setTenBenh("Benh " + i);
			benhServiceImpl.createBenh(benh);
		}
		for(int i = 4; i > 0; i --) {
			ChiTietPhieuKham ctKham = new ChiTietPhieuKham();
			ctKham.setTrieuChung("Trieu chung " + i);
			ctKham.setBenh(benhServiceImpl.findByName("Benh " + i));
			ctKham.setHoSoBenhAn(hoSoBenhAnImpl.findByName("BenhNhan" + i));
			chiTietPhieuKhamImpl.createChiTietPhieuKham(ctKham);
		}
		for(int i = 3; i > 0; i --) {
			ToaThuoc thuoc = new ToaThuoc();
			thuoc.setChiTietPhieuKham(chiTietPhieuKhamImpl.findAll().get(i));
			thuocToaThuocImpl.createToaThuoc(thuoc);
		}
		for(int i = 3; i > 0; i --) {
			ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
			chiTietToaThuoc.setSoLuong(8 + i);
			chiTietToaThuoc.setThuoc(thuocImpl.findAll().get(i));
//			chiTietToaThuoc.setToaThuoc(thuocToaThuocImpl.findByIdPK(null));
			chiTietToaThuocImpl.createChiTietToaThuoc(chiTietToaThuoc);
		}
		
	}
	private static void findAllPhieuKham() { 
		ChiTietPhieuKhamImpl ctPhieuKhamImpl = new ChiTietPhieuKhamImpl();
		ctPhieuKhamImpl.findAll();
	}
	private static HoSoBenhAn findByIdHoSoBenhAn() { 
		HoSoBenhAnImpl hoSoBenhAnImpl = new HoSoBenhAnImpl();
		return hoSoBenhAnImpl.findById("956935ce-6bef-4b2f-a8c7-aed30ae0f2dc");
	}
	private static void insertHoSoBenhAn() {
		HoSoBenhAnImpl hoSoBenhAnImpl = new HoSoBenhAnImpl();
		ChiTietPhieuKhamImpl ctPhieuKhamImpl = new ChiTietPhieuKhamImpl();
		
		ChiTietPhieuKham ctKham = new ChiTietPhieuKham();
		
		HoSoBenhAn hoSoBenhAn = new HoSoBenhAn();
		hoSoBenhAn.setDiaChi("DiaChi11");
		hoSoBenhAn.setDienThoai("000099991");
		hoSoBenhAn.setHoTen("BenhNhan11");
		hoSoBenhAn.setTuoi(19);
		
		hoSoBenhAnImpl.createHoSoBenhAn(hoSoBenhAn);
	}
	public String setLocalTimeZone(Date date) {
		String pattern = "dd-MM-yyyy HH:mm:ss.SSSZ";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		simpleDateFormat.setTimeZone(TimeZone.getDefault());
		return simpleDateFormat.format(date);
	}
	private static void findAllChiTietPhieuKham() { 
		ChiTietPhieuKhamImpl ctPhieuKhamImpl = new ChiTietPhieuKhamImpl();
		ctPhieuKhamImpl.findAll();
	}
	private static void findByIdChiTietPhieuKham() { 
		ChiTietPhieuKhamImpl ctPhieuKhamImpl = new ChiTietPhieuKhamImpl();
		ChiTietPhieuKham c = ctPhieuKhamImpl.findById("f3937426-97f1-4a81-8ad0-79dee961c132");
		System.out.println(c.getBenh());;
	}
	private static void insertChiTietPhieuKham() {
		BenhServiceImpl benhDao = new BenhServiceImpl();
		ChiTietPhieuKhamImpl ctPhieuKhamImpl = new ChiTietPhieuKhamImpl();
		Benh benh = benhDao.findById("d3c2394d-beba-401b-8d93-37406b94321c");
		ChiTietPhieuKham ctKham = new ChiTietPhieuKham();
		ctKham.setTrieuChung("Trieu chung 3");
		ctKham.setBenh(benh);
		ctPhieuKhamImpl.createChiTietPhieuKham(ctKham);
	}
	
	private static void insertBenh() {
		BenhServiceImpl benhDao = new BenhServiceImpl();
		Benh benh = new Benh();
		benh.setTenBenh("Benh1");
		benhDao.createBenh(benh);
	}
	
	private static void updateBenh() {
		BenhServiceImpl benhDao = new BenhServiceImpl();
		Benh benhUpdate = benhDao.findById("df84c7aa-9375-4cdb-91b1-963e224bade7");
		if (!Objects.isNull(benhUpdate)) {
			System.out.println("benh update: " + benhUpdate);
			benhUpdate.setTenBenh("BenhMoi1");
			benhDao.updateBenh(benhUpdate);
			
		} else {
			System.out.println("Cannot find benh with id1 = " + benhUpdate.getId());
		}
	}
	
	private static void deleteBenh(String id) {
		BenhServiceImpl benhDao = new BenhServiceImpl();
		String statusUpdate = benhDao.deleteById(id);
		if ( statusUpdate == CrudStatus.CAN_NOT_FIND) {
			System.out.println("Cannot find benh with id = " + id);
		} else if (statusUpdate == CrudStatus.FAIL) {
			System.out.println("deleteBenh with id = " + id + " fail!");
		} else {
			System.out.println("deleteBenh with id = " + id + " success!");
		}
	}
	
	///
	private static void insertThuoc() {
		ThuocImpl thuocImpl = new ThuocImpl();
		Thuoc thuoc = new Thuoc();
		thuoc.setTenThuoc("Thuoc 1");
		thuoc.setHuongDanSuDung("Huong Dan 1");
		thuocImpl.createThuoc(thuoc);
	}
	
	private static void updateThuoc() {
		ThuocImpl thuocImpl = new ThuocImpl();
		Thuoc thuocUpdate = thuocImpl.findById("4d2df907-bd93-4877-b67b-7c5ae1cff1ca");
		if (!Objects.isNull(thuocUpdate)) {
			System.out.println("Thuoc update: " + thuocUpdate);
			thuocUpdate.setTenThuoc("TenThuocMoi111");
			thuocImpl.updateThuoc(thuocUpdate);
			
		} else {
			System.out.println("Cannot find thuoc with id1 = " + thuocUpdate.getId());
		}
	}
	
	private static void deleteThuoc(String id) {
		ThuocImpl thuocImpl = new ThuocImpl();
		String statusUpdate = thuocImpl.deleteById(id);
		if ( statusUpdate == CrudStatus.CAN_NOT_FIND) {
			System.out.println("Cannot find thuoc with id = " + id);
		} else if (statusUpdate == CrudStatus.FAIL) {
			System.out.println("deleteThuoc with id = " + id + " fail!");
		} else {
			System.out.println("deleteThuoc with id = " + id + " success!");
		}
	}
	
	private static void findAllThuoc() { 
		ThuocImpl thuocImpl = new ThuocImpl();
		thuocImpl.findAll();
	}
	private static Thuoc findByIdThuoc(String id) { 
		ThuocImpl thuocImpl = new ThuocImpl();
		Thuoc c = thuocImpl.findById(id);
		System.out.println("id"+c);
		return c;
	}
	private static Thuoc findByIdName(String name) { 
		ThuocImpl thuocImpl = new ThuocImpl();
		Thuoc c = thuocImpl.findByName(name);
		System.out.println("name"+c);
		return c;
	}
	
	private static void insertChiTietToaThuoc() {
		ChiTietToaThuocImpl chiTietToaThuocImpl = new ChiTietToaThuocImpl();
		ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc();
		chiTietToaThuoc.setSoLuong(5);
		chiTietToaThuoc.setThuoc(findByIdThuoc("fb7b9317-bc1a-446c-8ca7-c839571a70d5"));
		chiTietToaThuocImpl.createChiTietToaThuoc(chiTietToaThuoc);
	}
	private static void findAllChiTietToaThuoc() { 
		ChiTietToaThuocImpl chiTietToaThuocImpl = new ChiTietToaThuocImpl();
		chiTietToaThuocImpl.findAll();
	}
	private static ChiTietToaThuoc findByIdChiTietToaThuoc(String id) { 
		ChiTietToaThuocImpl chiTietToaThuocImpl = new ChiTietToaThuocImpl();
		ChiTietToaThuoc c = chiTietToaThuocImpl.findById(id);
		System.out.println("id"+c);
		return c;
	}
	
	private static void insertToaThuoc() {
		ToaThuocImpl thuocImpl = new ToaThuocImpl();
		ToaThuoc thuoc = new ToaThuoc();
		thuocImpl.createToaThuoc(thuoc);
	}
	private static void findAllToaThuoc() { 
		ToaThuocImpl thuocImpl = new ToaThuocImpl();
		thuocImpl.findAll();
	}
	private static ToaThuoc findByIdToaThuoc(String id) { 
		ToaThuocImpl thuocImpl = new ToaThuocImpl();
		ToaThuoc c = thuocImpl.findById(id);
		System.out.println("id"+c);
		return c;
	}
}