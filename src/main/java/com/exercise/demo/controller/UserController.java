package com.exercise.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

}
