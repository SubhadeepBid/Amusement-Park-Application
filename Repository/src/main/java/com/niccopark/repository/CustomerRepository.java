package com.niccopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.niccopark.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByUsernameAndPassword(String username, String password);

	public Customer findByUsername(String username);

}
