package com.learnnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnnow.service.UserEntityService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserEntityController {
	 @Autowired
	    private UserEntityService userEntityService;

	    @GetMapping("/{id}")
	    public ResponseEntity<?> getUserById(@PathVariable Long id) {
	        return userEntityService.getUserById(id);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
	        return userEntityService.softDeleteUser(id);
	    }

	    @GetMapping
	    public ResponseEntity<?> getAllUsers() {
	        return userEntityService.getAllActiveUsers();
	    }
}
