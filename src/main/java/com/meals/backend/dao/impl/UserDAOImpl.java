package com.meals.backend.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meals.backend.dao.UserDAO;
import com.meals.backend.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User getUser(String userName, String passWord) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User c WHERE c.userName = :userName AND c.password = :password");
		query.setParameter("userName", userName);
		query.setParameter("password", passWord);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Boolean saveUser(User obj) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(obj);
		return true;
	}

}
