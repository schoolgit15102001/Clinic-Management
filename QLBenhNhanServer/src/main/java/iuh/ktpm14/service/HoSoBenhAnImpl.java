package iuh.ktpm14.service;

import java.util.List;
import java.util.Objects;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import iuh.ktpm14.entity.HoSoBenhAn;
import util.Connect;



public class HoSoBenhAnImpl implements HoSoBenhAnService{
	private OgmSessionFactory sessionFactory;

	public HoSoBenhAnImpl() {
	    sessionFactory = Connect.getInstance().getSessionFactory();
	}

	@Override
	public boolean createHoSoBenhAn(HoSoBenhAn hoSoBenhAn) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      session.save(hoSoBenhAn);
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
	public HoSoBenhAn findByPhoneNumber(String phoneNumber) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	    	String queryString = "db.hoSoBenhAns.find({dienThoai: '" + phoneNumber + "'})";
	      tr.begin();
	      List<HoSoBenhAn> hoSoBenhAns = session.createNativeQuery(queryString, HoSoBenhAn.class).getResultList();
	      hoSoBenhAns.forEach(x -> System.out.println("findByName: " + x));
	      tr.commit();
	      return hoSoBenhAns.get(0);
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}
	
	
}
