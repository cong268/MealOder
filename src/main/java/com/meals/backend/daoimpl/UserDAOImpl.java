package com.meals.backend.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meals.backend.dao.UserDAO;
import com.meals.backend.model.User;

@Repository
//@Transactional(value = "userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> getAllUser() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<User> list = null;
		Query query = session.createQuery("FROM User");
		list = query.list();
//		session.getTransaction().commit();
//	    session.close();
	    return list;
	}

}
