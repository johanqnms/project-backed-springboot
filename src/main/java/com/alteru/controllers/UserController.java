package com.alteru.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alteru.models.User;
import com.alteru.repository.UsersRepository;

@RestController
@RequestMapping("/api/v2/")
public class UserController {
	
	@Autowired
	private UsersRepository repository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/users")
	public List<User> getUsers() {
		return repository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		return repository.save(user);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		
		User user = repository.findById(id).orElseThrow();
		return ResponseEntity.ok(user);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userDetails){
		User user = repository.findById(id).orElseThrow();
		user.setDocument(userDetails.getDocument());
		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		user.setGender(userDetails.getGender());
		
		User userUpdated = repository.save(user);
		return ResponseEntity.ok(userUpdated);
		
	}	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable Long id) {
		User user = repository.findById(id).orElseThrow();
		repository.delete(user);
		
		//Response
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


}
