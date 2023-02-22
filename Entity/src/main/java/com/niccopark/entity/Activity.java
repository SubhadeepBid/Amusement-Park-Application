package com.niccopark.entity;

import java.util.List;

import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer activityId;
	
	private String name;
	
	private String description;
	
	private Float charges;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> tickets = new ArrayList<>();
	
	@ManyToMany
	private Set<Slot> slots = new HashSet<>();
	
}

// NEW FUNCTIONS

// addSlotToActivity(); // Activity Service

// getAllSlotsForAnActivity();  // Activity Service
