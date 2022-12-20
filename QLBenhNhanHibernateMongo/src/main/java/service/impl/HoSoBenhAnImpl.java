package service.impl;

import java.util.List;
import java.util.Objects;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import constant.CrudStatus;
import entity.Benh;
import entity.HoSoBenhAn;
import service.HoSoBenhAnService;
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
	public List<HoSoBenhAn> findAll() {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      String queryString = "FROM HoSoBenhAn";
	      tr.begin();
	      List<HoSoBenhAn> hoSoBenhAns = session.createQuery(queryString, HoSoBenhAn.class).getResultList();
	      hoSoBenhAns.forEach(x -> System.out.println("findAllHoSoBenhAn: " + x));
	      tr.commit();
	      return hoSoBenhAns;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	@Override
	public HoSoBenhAn findById(String id) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      HoSoBenhAn hoSoBenhAn = session.find(HoSoBenhAn.class, id);
	      System.out.println("findById: " + id + " HoSoBenhAn: " + hoSoBenhAn);
	      tr.commit();
	      return hoSoBenhAn;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	@Override
	public HoSoBenhAn findByName(String name) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	    	String queryString = "db.hoSoBenhAns.find({hoTen: '" + name + "'})";
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

	@Override
	public boolean updateHoSoBenhAn(HoSoBenhAn hoSoBenhAn) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();

		try {
			tr.begin();
		    session.update(hoSoBenhAn);
		    tr.commit();
			return true;
		} catch (Exception e) {
			System.out.println("Cannot find hoSoBenhAn with id = " + hoSoBenhAn.getId());
			tr.rollback();
		}
		return false;
	}

	@Override
	public String deleteByName(String name) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();

		try {
			tr.begin();
			HoSoBenhAn hoSoBenhAn = findByName(name);
			if (!Objects.isNull(hoSoBenhAn)) {
				session.delete(hoSoBenhAn);
			} else {
				return CrudStatus.CAN_NOT_FIND;
			}
		    
		    tr.commit();
			return CrudStatus.SUCCESS;
		} catch (Exception e) {
			System.out.println("Cannot delete benh with name = " + name);
			tr.rollback();
		}
		return CrudStatus.FAIL;
	}

	@Override
	public String deleteById(String id) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();

		try {
			tr.begin();
			HoSoBenhAn hoSoBenhAn = session.find(HoSoBenhAn.class, id);
			if (!Objects.isNull(hoSoBenhAn)) {
				session.delete(hoSoBenhAn);
			} else {
				tr.commit();
				return CrudStatus.CAN_NOT_FIND;
			}
		    
		    tr.commit();
			return CrudStatus.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot delete hoSoBenhAn with id = " + id);
			tr.rollback();
		}
		return CrudStatus.FAIL;
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
