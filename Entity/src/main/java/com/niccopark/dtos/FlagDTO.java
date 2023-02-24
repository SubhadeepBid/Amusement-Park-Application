package com.niccopark.dtos;

import com.niccopark.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlagDTO {
	
	private boolean flag;
	
	private String username;
	
	private Role role;

}
