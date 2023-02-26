package com.niccopark.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "slots")
	private List<Activity> activities = new ArrayList<>();

	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
