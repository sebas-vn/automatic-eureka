package com.revature.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.User;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Integer>{
	
	/**
	 * Property Expressions (look into spring docs to know what else you can do with these)
	 * Property Expressions infer SQL statements on entitites based on the DIRECT
	 * properties of the entity meing managed
	 */
	
	// This will automatically generate a SQL statement that uses DQL (with a WHERE clause)
	// to target the USer table and search for the record with the lastName passed
	
	public User findByLastName(String lastName);
	
	public Optional<User> findByUsername(String username);
	
	public User findByFirstNameIgnoreCase(String firstName);
	
	@Query(value="FROM User WHERE email LIKE %:substring")
	public User findByEmailContains(String substring); // if we pass in ith@mail.com -> return johnsmith@gmail.com
}
