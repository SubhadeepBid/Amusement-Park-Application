package com.niccopark.entity;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity 
@NoArgsConstructor
@AllArgsConstructor
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer slotId;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	@ManyToMany(mappedBy = "slots")
	private Set<Activity> activities = new HashSet<>();

}


// NEW FUNCTIONS

// addSlot()  // Admin Service

// getAllActivitiesFromSlot(); // Admin



