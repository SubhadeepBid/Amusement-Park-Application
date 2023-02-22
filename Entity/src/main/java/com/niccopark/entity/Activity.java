package com.niccopark.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private String description;
	
	private Float charges;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Ticket> tickets;
	
	private String name;
	
//	private Duration ;

}
