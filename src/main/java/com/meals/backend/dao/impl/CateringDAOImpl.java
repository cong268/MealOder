package com.meals.backend.dao.impl;

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

}
