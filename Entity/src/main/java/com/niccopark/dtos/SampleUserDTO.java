package com.niccopark.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleUserDTO {

	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	@Size(min = 3, message = "Name length always greater than 2 character")
	private String name;

	@NotNull(message = "Name can't be null")
	@Pattern(regexp = "^[a-zA-Z0-9_]{6,12}$", message = "username length should be bgreater than 5 and less than 13 and contains only alphanumeric value with _")
	@Column(unique = true)
	private String username;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must contain 8 characters and should have atleast 1 Upper Case, 1 Small Case, 1 Number and 1 Special Character")
	private String password;

	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	@Size(min = 3, message = "Address length always greater than 2 character")
	private String address;

	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Invalid Mobile Number.")
	private String mobileNumber;

	@Email(message = "Email is not in format")
	@NotNull(message = "Email can't be null")
	@NotBlank(message = "Email can't be blank")
	@Column(unique = true)
	private String email;

}
