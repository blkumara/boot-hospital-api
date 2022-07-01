package com.ty.hospitalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hospitalapi.dto.Login;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.dto.User;
import com.ty.hospitalapi.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/users")
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@GetMapping("/users")
	public ResponseStructure<List<User>> getAllUser() {
		return service.getAllUser();
	}

	@GetMapping("/users/{id}")
	public ResponseStructure<User> getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}

	@DeleteMapping("/users")
	public ResponseStructure<String> deleteUser(@RequestParam int id) {
		return service.deleteUser(id);
	}

	@PutMapping("/users")
	public ResponseStructure<User> updateUser(@RequestParam int id, @RequestBody User user) {
		return service.updateUser(user, id);
	}

	@PostMapping("/users/login")
	public ResponseStructure<User> validateUser(@RequestBody Login login) {
		return service.validateUser(login.getEmail(), login.getPassword());
	}
}
