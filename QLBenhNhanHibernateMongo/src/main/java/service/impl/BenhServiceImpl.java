package service.impl;

import java.util.List;
import java.util.Objects;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import constant.CrudStatus;
import entity.Benh;
import service.BenhService;
import util.Connect;

public class BenhServiceImpl implements BenhService{
	private OgmSessionFactory sessionFactory;

	public BenhServiceImpl() {
	    sessionFactory = Connect.getInstance().getSessionFactory();
	}
	
	@Override
	public boolean createBenh(Benh benh) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      session.save(benh);
	      tr.commit();
	      return true;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return false;
	}

	@Override
	public List<Benh> findAll() {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      String queryString = "FROM Benh";
	      tr.begin();
	      List<Benh> benhs = session.createQuery(queryString, Benh.class).getResultList();
	      benhs.forEach(x -> System.out.println("findAllBenh: " + x));
	      tr.commit();
	      return benhs;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	
	@Override
	public Benh findById(String id) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    Benh benh = new Benh();
	    try {
	      tr.begin();
	      benh = session.find(Benh.class, id);
	      System.out.println("findById: " + id + " - Benh: " + benh);
	      tr.commit();
	      return benh;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	@Override
	public String deleteById(String id) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();

		try {
			tr.begin();
			Benh benhDelete = session.find(Benh.class, id);
			if (!Objects.isNull(benhDelete)) {
				session.delete(benhDelete);
			} else {
				tr.commit();
				return CrudStatus.CAN_NOT_FIND;
			}
		    
		    tr.commit();
			return CrudStatus.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot delete benh with id = " + id);
			tr.rollback();
		}
		return CrudStatus.FAIL;
	}
	
	@Override
	public Benh findByName(String name) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	    	String queryString = "db.benhs.find({tenBenh: '" + name + "'})";
	      tr.begin();
	      List<Benh> benhs = session.createNativeQuery(queryString, Benh.class).getResultList();
	      benhs.forEach(x -> System.out.println("findByName: " + x + " benh: " + benhs.get(0)));
	      tr.commit();
	      return benhs.get(0);
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	
	@Override
	public String deleteByName(String name) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    
	    
		try {
			tr.begin();
			
			Benh benhDelete = new Benh();
	
			benhDelete = findByName(name);
			
			System.out.println("KQ2: "+benhDelete);
			if (!Objects.isNull(benhDelete)) {
				session.delete(benhDelete);
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
	public boolean updateBenh(Benh benhNew){
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();

		try {
			tr.begin();
		    session.update(benhNew);
		    tr.commit();
			return true;
		} catch (Exception e) {
			System.out.println("Cannot find benh with id = " + benhNew.getId());
			tr.rollback();
		}
		return false;
	}
}
