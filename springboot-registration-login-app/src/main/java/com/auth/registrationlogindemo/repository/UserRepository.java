package com.auth.registrationlogindemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.registrationlogindemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String email);
    
}
