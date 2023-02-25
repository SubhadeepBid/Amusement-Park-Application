package com.niccopark.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserUsernameDTO {

	@NotNull(message = "Username can't be null")
	@Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$", message = "username length should be bgreater than 5 and less than 13 and contains only alphanumeric value with _")
	private String newUsername;

	private String password;

}
