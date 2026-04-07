package com.jobtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.entity.User;
import com.jobtracker.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private UserService userService;
	
	// REGISTER API
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		System.out.println("AuthController.register()");
		return userService.registerUser(user);
	}
	
	// LOGIN API
//	@PostMapping("/login")
//	public User login(@RequestParam String email,
//					  @RequestParam String password) {
//		System.out.println("AuthController.login()");
//		return userService.loginUser(email, password);
//	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
	    return userService.loginUser(user.getEmail(), user.getPassword());
	}
}
