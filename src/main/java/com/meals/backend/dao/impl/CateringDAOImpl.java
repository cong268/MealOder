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
	public List<Catering> getLstByDepartment(List<String> staffIds, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"FROM Catering c WHERE c.staffId IN :staffs AND c.cateringDate = :cateringDate AND c.ordered = 1 AND c.status = 0 AND c.catered = 0");
		query.setParameter("staffs", staffIds);
		query.setParameter("cateringDate", date);
		return query.list();
	}

}
