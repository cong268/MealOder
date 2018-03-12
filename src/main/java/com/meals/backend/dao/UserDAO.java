package com.meals.backend.dao;

import com.meals.backend.model.User;

public interface UserDAO {
	User getUser (String userName, String passWord);
	Boolean saveUser (User obj);
}
