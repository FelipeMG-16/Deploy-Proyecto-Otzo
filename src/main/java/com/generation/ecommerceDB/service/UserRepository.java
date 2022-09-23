package com.generation.ecommerceDB.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.generation.ecommerceDB.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username=?1") //el signo de interrogacion es el parametro.
	Optional<User> findByUsername (String username);
	

}//UserRepository
