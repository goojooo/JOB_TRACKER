package com.jobtracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobtracker.entity.User;
import com.jobtracker.repository.UserRepository;

@Service

public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	// REGISTER
//	public User registerUser(User user) {
//		return userRepository.save(user);
//	}
	
	public User registerUser(User user) {
	    System.out.println("User: " + user.getEmail());
	    return userRepository.save(user);
	    
	}
	
	// LOGIN
	public User loginUser(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(user.isPresent() && user.get().getPassword().equals(password)) {
			return user.get();
		} else {
			throw new RuntimeException("Invalid email or password");
		}
	}
}


//<dependency>
//<groupId>org.springframework.boot</groupId>
//<artifactId>spring-boot-starter-security</artifactId>
//</dependency>