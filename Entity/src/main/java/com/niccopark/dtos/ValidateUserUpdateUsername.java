package com.niccopark.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateUserUpdateUsername {
	
	private String newUsername;

	private String oldUsername;

	private String password;
	
	
}
