package com.niccopark.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserUsernameDTO {
	
	private String newUsername;

	private String oldUsername;

	private String password;
	
	
}
