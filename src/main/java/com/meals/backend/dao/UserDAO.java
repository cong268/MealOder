package com.meals.backend.dao;

import com.meals.backend.model.Users;

public interface UserDAO {
	Users getUser (String userName, String passWord);
	Boolean saveUser (Users obj);
	Boolean checkExitsUser (String userName);
	Boolean checkInvalidPassword(String userName, String password);
	Integer getUserId(String userName);
}
