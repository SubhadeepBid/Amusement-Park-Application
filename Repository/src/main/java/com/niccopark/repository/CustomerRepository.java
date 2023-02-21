package com.niccopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
