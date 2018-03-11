package com.meals.backend.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meals.backend.dao.PersonDAO;
import com.meals.backend.model.Person;
@Repository
@Transactional (value = "personDAO")
public class PersonDAOImpl implements PersonDAO{
	@Autowired
	 private SessionFactory sessionFactory;

	 public void setSessionFactory(SessionFactory sessionFactory) {
	     this.sessionFactory = sessionFactory;
	 }

	public List<Person> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Person> list = null;
		Query query = session.createQuery("FROM Person");
		return query.list();
	}

}
