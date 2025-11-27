package com.url.shortner.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.dtos.LoginRequest;
import com.url.shortner.dtos.RegisterRequest;
import com.url.shortner.models.User;
import com.url.shortner.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private UserService userService;
	
public AuthController(UserService userService) {
		this.userService = userService;
	}

@PostMapping("/public/login")
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest ){
	return ResponseEntity.ok(userService.authenticateUser(loginRequest));
}


@PostMapping("/public/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest register ){
		User user=new User();
		user.setUsername(register.getUsername());
		user.setEmail(register.getEmail());
		user.setRole("ROLE_USER");
		user.setPassword(register.getPassword());
		userService.registerUser(user);
		return ResponseEntity.ok("User Registered Successfully");
		
	}



}
