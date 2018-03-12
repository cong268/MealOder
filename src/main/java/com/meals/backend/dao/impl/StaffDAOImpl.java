package com.meals.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meals.backend.dao.StaffDAO;
import com.meals.backend.model.Staff;

@Repository("staffDAO")
@Transactional
public class StaffDAOImpl implements StaffDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Staff getStaffByUserId(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Staff c WHERE c.staffId = (SELECT u.staffId FROM User u WHERE u.userId = :userId)");
		query.setParameter("userId", userId);
		if (query.list() != null && !query.list().isEmpty()) {
			return (Staff) query.list().get(0);
		}
		return null;
	}

	@Override
	public List<Staff> getStaffByDepart(Integer departId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Staff c WHERE c.deptId = :deptId");
		query.setParameter("deptId", departId);
		return query.list();
	}

}
