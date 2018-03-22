package com.meals.backend.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meals.backend.dao.CateringDAO;
import com.meals.backend.model.Catering;

@Repository("cateringDAO")
@Transactional
public class CateringDAOImpl implements CateringDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Boolean saveOrUpdate(Catering obj) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(obj);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Catering> getLstByOder(Integer departId, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.deptId = :deptId AND c.id.cateringDate = :cateringDate AND c.ordered = 1 AND c.status = 0 AND c.catered = 0 AND c.disable = 0");
		query.setParameter("deptId", departId);
		query.setParameter("cateringDate", date);
		return query.list();
	}

	@Override
	public List<Catering> getLstByStatus(Date cateringDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.id.cateringDate = :cateringDate AND c.ordered = 1 AND c.status = 1 AND c.catered = 0 AND c.disable = 0");
		query.setParameter("cateringDate", cateringDate);
		return query.list();
	}

	@Override
	public List<Catering> getLstByDate(Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.id.cateringDate >= :fromDate AND c.id.cateringDate < :toDate AND c.catered = 1 AND c.ordered = 1 AND c.status = 1 AND c.disable = 0");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public List<Object[]> getLstAndCount(Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("SELECT c.id.cateringDate, c.deptId, c.mealId, c.id.mealTimeId, c.locationId, COUNT(*) "
						+ "FROM Catering c WHERE c.id.cateringDate >= :fromDate AND c.id.cateringDate < :toDate "
						+ "AND c.catered = 1 AND c.disable = 0 GROUP BY c.id.cateringDate, c.deptId, c.mealId, c.id.mealTimeId, c.locationId "
						+ "ORDER BY c.id.cateringDate ASC");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public Boolean saveList(List<Catering> lst) {
		if (lst != null && !lst.isEmpty()) {
			Session session = sessionFactory.getCurrentSession();
			try {
				for (int i = 0; i < lst.size(); i++) {
					Catering obj = lst.get(i);
					session.saveOrUpdate(obj);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public Catering getCateringById(String staffId, Date date, Integer mealTimeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.id.staffId = :staffId AND c.id.cateringDate = :date AND c.id.mealTimeId = :mealTimeId");
		query.setParameter("staffId", staffId);
		query.setParameter("date", date);
		query.setParameter("mealTimeId", mealTimeId);
		List<Catering> lst = query.list();
		if (lst != null && !lst.isEmpty()) {
			return lst.get(0);
		}
		return null;
	}

	// @Override
	// public List<Catering> getByDepartAndDate(Integer deptId, Date fromDate,
	// Date toDate, boolean ordered,
	// boolean status, boolean catered, boolean disable) {
	// Session session = sessionFactory.getCurrentSession();
	// Query query = session.createQuery(
	// "FROM Catering c WHERE c.deptId = :deptId AND c.id.cateringDate >=
	// :fromDate AND c.id.cateringDate < :toDate");
	// query.setParameter("deptId", deptId);
	// query.setParameter("fromDate", fromDate);
	// query.setParameter("toDate", toDate);
	// return query.list();
	// }

	@Override
	public Boolean deleteById(String staffId, Integer mealTimeId, Date cateringDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"DELETE Catering c WHERE c.id.staffId = :staffId AND c.id.cateringDate = :date AND c.id.mealTimeId = :mealTimeId");
		query.setParameter("staffId", staffId);
		query.setParameter("date", cateringDate);
		query.setParameter("mealTimeId", mealTimeId);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Catering> getHistoryStaff(String staffId, Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Catering c WHERE c.id.staffId = :staffId AND c.id.cateringDate >= :fromDate "
						+ "AND c.id.cateringDate < :toDate AND c.disable = 0");
		query.setParameter("staffId", staffId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public List<Catering> getExportStaffByManager(String staffId, Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Catering c WHERE c.id.staffId = :staffId AND c.id.cateringDate >= :fromDate "
						+ "AND c.id.cateringDate < :toDate AND c.status = 1 AND c.ordered = 1 AND c.disable = 0");
		query.setParameter("staffId", staffId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public List<Catering> getExportStaffByAdmin(String staffId, Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Catering c WHERE c.id.staffId = :staffId AND c.id.cateringDate >= :fromDate "
						+ "AND c.id.cateringDate < :toDate AND c.catered = 1 AND c.status = 1 AND c.ordered = 1 AND c.disable = 0");
		query.setParameter("staffId", staffId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public List<Catering> getExportDepartByManager(Integer deptId, Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Catering c WHERE c.deptId = :deptId AND c.id.cateringDate >= :fromDate "
				+ "AND c.id.cateringDate < :toDate AND c.status = 1 AND c.ordered = 1 AND c.disable = 0");
		query.setParameter("deptId", deptId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public List<Catering> getExportDepartByAdmin(Integer deptId, Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Catering c WHERE c.deptId = :deptId AND c.id.cateringDate >= :fromDate "
				+ "AND c.id.cateringDate < :toDate AND c.catered = 1 AND c.status = 1 AND c.ordered = 1 AND c.disable = 0");
		query.setParameter("deptId", deptId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public Boolean updateReject(String staffId, Date date, Integer mealTimeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Catering c SET c.disable = 1 WHERE c.id.staffId = :staffId AND c.id.cateringDate = :date AND c.id.mealTimeId = :mealTimeId");
		query.setParameter("staffId", staffId);
		query.setParameter("date", date);
		query.setParameter("mealTimeId", mealTimeId);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateCatered(String staffId, Date date, Integer mealTimeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE Catering c SET c.catered = 1 WHERE c.id.staffId = :staffId AND c.id.cateringDate = :date AND c.id.mealTimeId = :mealTimeId");
		query.setParameter("staffId", staffId);
		query.setParameter("date", date);
		query.setParameter("mealTimeId", mealTimeId);
		int result = query.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Catering> getLstApproOfStaff(String staffId, Date cateringDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.id.cateringDate = :cateringDate AND c.id.staffId = :staffId AND c.status = 1 AND c.ordered = 1 AND c.disable = 0");
		query.setParameter("cateringDate", cateringDate);
		query.setParameter("staffId", staffId);
		return query.list();
	}
}
