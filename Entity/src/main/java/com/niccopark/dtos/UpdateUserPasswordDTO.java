package com.niccopark.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordDTO {

	private String oldPassword;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must contain 8 characters and should have atleast 1 Upper Case, 1 Small Case, 1 Number and 1 Special Character")
	private String newPassword;

}
