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
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(obj);
		return true;
	}

	@Override
	public List<Catering> getLstByOder(Integer departId, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.departId = :departId AND c.cateringDate = :cateringDate AND c.ordered = 1 AND c.status = 0 AND c.catered = 0");
		query.setParameter("departId", departId);
		query.setParameter("cateringDate", date);
		return query.list();
	}

	@Override
	public List<Catering> getLstByStatus(Date cateringDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.cateringDate = :cateringDate AND c.ordered = 1 AND c.status = 1 AND c.catered = 0");
		query.setParameter("cateringDate", cateringDate);
		return query.list();
	}

	@Override
	public List<Catering> getLstByDate(Date fromDate, Date toDate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.cateringDate >= :fromDate AND c.cateringDate < :toDate AND c.catered = 1");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
	}

	@Override
	public Catering getByStaffId(String staffId, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Catering c WHERE c.staffId = :staffId AND c.cateringDate = :cateringDate");
		query.setParameter("staffId", staffId);
		query.setParameter("cateringDate", date);
		if (query.list() != null && !query.list().isEmpty()) {
			return (Catering) query.list().get(0);
		}
		return null;
	}
}
