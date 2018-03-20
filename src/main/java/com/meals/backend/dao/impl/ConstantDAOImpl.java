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
import com.meals.backend.model.Location;
import com.meals.backend.model.Meal;
import com.meals.backend.model.MealTime;
import com.meals.backend.model.UserRole;

@Repository("constantDAO")
@Transactional
public class ConstantDAOImpl implements ConstantDAO {
  @Autowired
  private SessionFactory sessionFactory;

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Department getDepartment(Integer id) {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Department c WHERE c.deptId = :deptId");
    query.setParameter("deptId", id);
    if (query.list() != null && !query.list().isEmpty()) {
      return (Department) query.list().get(0);
    }
    return null;
  }

  @Override
  public UserRole getRoleById(Integer id) {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM UserRole c WHERE c.userRoleId = :userRoleId");
    query.setParameter("userRoleId", id);
    List<UserRole> list = query.list();
    if (list != null && !list.isEmpty()) {
      return list.get(0);
    }
    return null;
  }

  @Override
  public List<Department> getAllDepartment() {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Department");
    return query.list();
  }

  @Override
  public List<Location> getAllLocation() {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Location");
    return query.list();
  }

  @Override
  public List<UserRole> getAllRole() {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM UserRole");
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
  public List<Meal> getAllMeal() {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM Meal");
    return query.list();
  }

  @Override
  public List<MealTime> getAllMealTime() {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("FROM MealTime");
    return query.list();
  }
}
