package com.meals.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meals.backend.dao.UserDAO;
import com.meals.backend.model.User;

@Controller
@RequestMapping("userController")
public class UserController {
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}
}
