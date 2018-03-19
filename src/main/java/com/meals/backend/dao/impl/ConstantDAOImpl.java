package com.meals.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.model.Allcode;
import com.meals.backend.model.Department;
import com.meals.backend.model.Location;
import com.meals.backend.model.Userrole;

@Repository("constantDAO")
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

	public Userrole getRoleById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Userrole c WHERE c.userRoleID = :userRoleID");
		query.setParameter("userRoleID", id);
		List<Userrole> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<Department> getAllDepartment() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Department");
		return query.list();
	}

	public List<Location> getAllLocation() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Location");
		return query.list();
	}

	@Override
	public List<Userrole> getAllRole() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Userrole");
		return query.list();
	}

	@Override
	public Location getLocationById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Location c WHERE c.locationId = :locationId");
		query.setParameter("locationId", id);
		if (query.list() != null && !query.list().isEmpty()) {
			return (Location) query.list().get(0);
		}
		return null;
	}

	@Override
	public List<Allcode> getLstByCodeVal(String codeVal) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Allcode a WHERE a.codeVal = :codeVal");
		query.setParameter("codeVal", codeVal);
		return query.list();
	}
}
