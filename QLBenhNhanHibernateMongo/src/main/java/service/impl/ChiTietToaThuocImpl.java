package service.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import entity.ChiTietPhieuKham;
import entity.ChiTietToaThuoc;
import entity.HoSoBenhAn;
import service.ChiTietToaThuocService;
import util.Connect;

public class ChiTietToaThuocImpl implements ChiTietToaThuocService{
	private OgmSessionFactory sessionFactory;

	public ChiTietToaThuocImpl() {
	    sessionFactory = Connect.getInstance().getSessionFactory();
	}
	@Override
	public boolean createChiTietToaThuoc(ChiTietToaThuoc chiTietToaThuoc) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      session.save(chiTietToaThuoc);
	      tr.commit();
	      System.out.println("Create Success!");
	      return true;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return false;
	}

	@Override
	public List<ChiTietToaThuoc> findAll() {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      String queryString = "FROM ChiTietToaThuoc";
	      tr.begin();
	      List<ChiTietToaThuoc> chiTietToaThuocs = session.createQuery(queryString, ChiTietToaThuoc.class).getResultList();
	      chiTietToaThuocs.forEach(x -> System.out.println("findAllChiTietToaThuoc: " + x));
	      tr.commit();
	      return chiTietToaThuocs;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	@Override
	public ChiTietToaThuoc findById(String id) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      ChiTietToaThuoc chiTietToaThuoc = session.find(ChiTietToaThuoc.class, id);
	      System.out.println("findById: " + id + " ChiTietToaThuoc: " + chiTietToaThuoc);
	      tr.commit();
	      return chiTietToaThuoc;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}
	
	
	@Override
	public List<ChiTietToaThuoc> findByIdToaThuoc(String idToaThuoc) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    System.out.println(idToaThuoc+ "  ben chi tiet");
	    try {
	    	String queryString = "db.chiTietToaThuocs.find({toaThuoc_id: '" + idToaThuoc + "'})";
	      tr.begin();
	      
	      List<ChiTietToaThuoc> chiTietPK = session.createNativeQuery(queryString, ChiTietToaThuoc.class).getResultList();
	      
	      chiTietPK.forEach(x -> System.out.println("findByIdHoSo: " + x));
	      tr.commit();
	      return chiTietPK;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available! findByIdHoSo");
	      tr.rollback();
	    }
	    return null;
	
	}

}
