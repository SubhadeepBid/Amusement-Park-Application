package com.niccopark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.niccopark.entity.Customer;
import com.niccopark.entity.Ticket;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByUsernameAndPassword(String username, String password);

	public Optional<Customer> findByUsername(String username);
	
	@Query("select tickets from Customer where customerId=?1")
	public List<Ticket> getAllTickets(Integer customerId);

}
