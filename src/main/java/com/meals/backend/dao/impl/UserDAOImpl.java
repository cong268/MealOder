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

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> getAllUser() {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = null;
		Query query = session.createQuery("FROM User");
		list = query.list();
	    return list;
	}

	public User getUserById(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User c WHERE c.userId = :userId");
		query.setParameter("userId", userId);
		if(query.list() != null && !query.list().isEmpty()){
			return (User) query.list().get(0);
		}
	    return null;
	}

}
