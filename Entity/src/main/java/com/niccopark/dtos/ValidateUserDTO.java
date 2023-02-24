package com.niccopark.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateUserDTO {
	
	@NotNull(message = "Name can't be null")
	@Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$", message = "username length should be bgreater than 5 and less than 13 and contains only alphanumeric value with _")
	private String username;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must contain 8 characters and should have atleast 1 Upper Case, 1 Small Case, 1 Number and 1 Special Character")
	private String password;

}
