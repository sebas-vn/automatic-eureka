package com.revature.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

// we have to tell spring that this class has the capability of processing web requests

@RestController // RestController is a specific type a control that assumes your'e returning a @responseBody
@RequestMapping("/users") // all methods and endpoints exposed at http://localhost:8090/api
public class UserController {
	
	// our controller needs to call its dependency which is userService
	@Autowired
	UserService userService;
	
	/**
	 * If someone sends a GET request here: http://localhost:8090/api/users
	 * they retrieve ALL users
	 */
	
	@GetMapping
	public ResponseEntity<Set<User>> findAll() {
		// ResponseEntity allows us to send back custom HTTP status & headers within the HTTP response
		return ResponseEntity.ok(userService.findAll());
	}
	
	// Get request that reads the id from the query parameter
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
	
		// call the service method, pass the captured id through and return it as a response entity with a 200 ok status
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username) {
		
		return ResponseEntity.ok(userService.findByUsername(username));
	}
	
	@PostMapping("/add") 				// The valid annotations makes sure that the User must comply with the restrictions we set in the 
	public ResponseEntity<User> addUser(@Valid @RequestBody User u) { // taking in the user object in the HTTP RequestBody

		return ResponseEntity.ok(userService.insert(u));
	}
}
