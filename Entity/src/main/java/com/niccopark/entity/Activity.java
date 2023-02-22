package com.niccopark.entity;

<<<<<<< HEAD
import java.util.List;

import jakarta.persistence.CascadeType;
=======
import java.util.HashSet;
import java.util.Set;

>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
=======
import jakarta.persistence.ManyToMany;
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
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
	
<<<<<<< HEAD
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ticket> tickets;
=======
	@ManyToMany
	private Set<Slot> slots = new HashSet<>();
	
}

// NEW FUNCTIONS

// addSlotToActivity(); // Activity Service

// getAllSlotsForAnActivity();  // Activity Service








	
//	private List<LocalDateTime> activityStartTime = new ArrayList<>();
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Ticket> tickets;
<<<<<<< HEAD
//	
//	
//	
=======
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
	
	private String name;
	
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
//	private Duration ;

/* 
OneToMany 

Slot
=====

slotId
startTime
endTime


@OneToMany
private List<Slot> slot;

*/