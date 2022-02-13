package com.alteru.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alteru.models.User;

@RestController
@RequestMapping("/api/v2/")
public class UserAuth {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("login")
	public boolean login(@RequestBody User user) {
		
		String query = "FROM User WHERE email=: email";
		List<User> userFound = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();
		return !userFound.isEmpty();	
	}

}
