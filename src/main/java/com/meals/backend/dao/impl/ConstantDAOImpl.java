package com.meals.backend.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.model.Department;

@Repository
@Transactional
public class ConstantDAOImpl implements ConstantDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Department getDepartment(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Department c WHERE c.deptId = :deptId");
		query.setParameter("deptId", id);
		if (query.list() != null && !query.list().isEmpty()) {
			return (Department) query.list().get(0);
		}
		return null;
	}
}
