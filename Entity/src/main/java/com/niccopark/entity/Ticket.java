package com.niccopark.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	// Apply @JsonIgnore here to avoid StackOverflow error
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	// Apply @JsonIgnore here to avoid StackOverflow error
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) // Added , fetch = FetchType.EAGER
	@JoinColumn(name = "activityId")
	private Activity activity;
	
	@OneToOne
	@JoinColumn(name = "slotId")
	private Slot slot;
	
	private LocalDate date;
	
	private LocalDateTime bookingTime = LocalDateTime.now();

//	@Override
//	public String toString() {
//		return "Ticket [ticketId=" + ticketId + ", bookingTime=" + bookingTime + "]";
//	}
	
}
