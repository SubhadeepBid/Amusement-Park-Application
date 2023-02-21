package com.niccopark.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
	
	private String username;
	
	private String password;
	
	private String address;
	
	private String mobileNumber;
	
	private String email;

}
