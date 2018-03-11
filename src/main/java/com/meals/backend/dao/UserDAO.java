package com.meals.backend.dao;

import java.util.List;

import com.meals.backend.model.User;

public interface UserDAO {
	User getUserById (Integer userId);
	List<User> getAllUser ();
}
