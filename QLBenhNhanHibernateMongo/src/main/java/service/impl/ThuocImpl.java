package service.impl;

import java.util.List;
import java.util.Objects;

import org.hibernate.Transaction;
import org.hibernate.ogm.OgmSession;
import org.hibernate.ogm.OgmSessionFactory;

import constant.CrudStatus;
import entity.Thuoc;
import service.ThuocService;
import util.Connect;

public class ThuocImpl implements ThuocService{
	private OgmSessionFactory sessionFactory;

	public ThuocImpl() {
	    sessionFactory = Connect.getInstance().getSessionFactory();
	}
	@Override
	public boolean createThuoc(Thuoc thuoc) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      session.save(thuoc);
	      tr.commit();
	      return true;
	    } catch (Exception e) {
	      e.printStackTrace();
	      tr.rollback();
	    }
	    return false;
	}

	@Override
	public List<Thuoc> findAll() {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      String queryString = "FROM Thuoc";
	      tr.begin();
	      List<Thuoc> thuocs = session.createQuery(queryString, Thuoc.class).getResultList();
	      //thuocs.forEach(x -> System.out.println("findAllThuoc: " + x));
	      tr.commit();
	      return thuocs;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	@Override
	public Thuoc findByName(String name) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	    	String queryString = "db.thuocs.find({tenThuoc: '" + name + "'})";
	      tr.begin();
	      List<Thuoc> thuocs = session.createNativeQuery(queryString, Thuoc.class).getResultList();
	      //thuocs.forEach(x -> System.out.println("findByName: " + x + " Thuoc: " + thuocs.get(0)));
	      tr.commit();
	      return thuocs.get(0);
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	@Override
	public Thuoc findById(String id) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();
	    try {
	      tr.begin();
	      Thuoc thuoc = session.find(Thuoc.class, id);
	      System.out.println("findById: " + id + " - Thuoc: " + thuoc);
	      tr.commit();
	      return thuoc;
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("No data available!");
	      tr.rollback();
	    }
	    return null;
	}

	@Override
	public boolean updateThuoc(Thuoc thuoc) {
		OgmSession session = sessionFactory.getCurrentSession();
	    Transaction tr = session.getTransaction();

		try {
			tr.begin();
		    session.update(thuoc);
		    tr.commit();
			return true;
		} catch (Exception e) {
			System.out.println("Cannot find thuoc with id = " + thuoc.getId());
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
			Thuoc thuocDelete = findByName(name);
			if (!Objects.isNull(thuocDelete)) {
				session.delete(thuocDelete);
			} else {
				return CrudStatus.CAN_NOT_FIND;
			}
		    
		    tr.commit();
			return CrudStatus.SUCCESS;
		} catch (Exception e) {
			System.out.println("Cannot delete thuoc with name = " + name);
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
			Thuoc thuocDelete = session.find(Thuoc.class, id);
			if (!Objects.isNull(thuocDelete)) {
				session.delete(thuocDelete);
			} else {
				tr.commit();
				return CrudStatus.CAN_NOT_FIND;
			}
		    
		    tr.commit();
			return CrudStatus.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot delete thuoc with id = " + id);
			tr.rollback();
		}
		return CrudStatus.FAIL;
	}

}
