package com.niccopark.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	
	@ManyToOne
	private Customer customer;
	
//	@OneToMany
//	private List<BookedActivity> bookedActivities = new ArrayList<>();
	
	@ManyToOne
	private Activity activity;
	
	@OneToOne
	private Slot slot;
	
	private LocalDateTime bookingTime = LocalDateTime.now();

}

// select Activity from Ticket where customer =:c
// findActivityByCustomer();







//@ManyToOne
//private Activity activity;
