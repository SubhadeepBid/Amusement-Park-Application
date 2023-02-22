package com.niccopark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.niccopark.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByUsernameAndPassword(String username, String password);

	public Optional<Customer> findByUsername(String username);

}
