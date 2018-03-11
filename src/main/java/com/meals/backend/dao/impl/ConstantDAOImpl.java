package com.meals.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.model.Department;
import com.meals.backend.model.UserRole;

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
		Query query = session.createQuery("FROM Department c WHERE c.deptId = :deptId");
		query.setParameter("deptId", id);
		if (query.list() != null && !query.list().isEmpty()) {
			return (Department) query.list().get(0);
		}
		return null;
	}

	public UserRole getRoleById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserRole c WHERE c.userRoleID = :userRoleID");
		query.setParameter("userRoleID", id);
		List<UserRole> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
