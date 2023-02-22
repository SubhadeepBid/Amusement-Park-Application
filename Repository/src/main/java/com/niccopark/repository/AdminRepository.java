package com.niccopark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	public Optional<Admin> findByUsername(String username);
	
	public Optional<Admin> findByUsernameAndPassword(String username, String password);

}
