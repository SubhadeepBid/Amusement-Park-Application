package com.niccopark.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "Name can't be blank")
	@NotNull(message = "Name can't be null")
	private String name;

	@NotBlank(message = "Descriptiona can't be blank")
	@NotNull(message = "Descriptiona can't be null")
	private String description;

	@DecimalMin(value = "100.00", message = "Charges can't be less than 100")
	@NotNull(message = "charges can't be null")
	private Float charges;

	// Apply @JsonIgnore here to avoid StackOverflow error
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER) // Removed cascade = CascadeType.ALL, 
	@JoinTable(joinColumns = @JoinColumn(name = "activityId"), inverseJoinColumns = @JoinColumn(name = "slotId"))
	private List<Slot> slots = new ArrayList<>();
//	private Set<Slot> slots = new HashSet<>(); // StackOverflow Error is coming

	@JsonIgnore
	@OneToMany(mappedBy = "activity") // Removed cascade = CascadeType.ALL, 
	private List<Ticket> tickets = new ArrayList<>();

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", name=" + name + ", description=" + description + ", charges="
				+ charges + ", slots=" + slots + "]";
	}

}
