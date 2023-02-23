package com.niccopark.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

	// Apply @JsonIgnore here to avoid StackOverflow error
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "activityId"), inverseJoinColumns = @JoinColumn(name = "slotId"))
	private List<Slot> slots = new ArrayList<>();
//	private Set<Slot> slots = new HashSet<>(); // StackOverflow Error is coming
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
	private List<Ticket> tickets = new ArrayList<>();

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", name=" + name + ", description=" + description + ", charges="
				+ charges + ", slots=" + slots + "]";
	}

}
