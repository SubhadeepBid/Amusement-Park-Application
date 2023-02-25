package com.niccopark.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {

	@Id
	private String uuid;

	private String username;

	private LocalDateTime sessionStart;
	
	private LocalDateTime sessionExpiry;
	
	@Enumerated(EnumType.STRING)
	private Role role;

}
