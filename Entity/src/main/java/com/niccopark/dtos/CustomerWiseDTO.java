package com.niccopark.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWiseDTO {
	
	private CustomerDetailsDTO customerDetails;
	
	private List<ActivityDetailsDTO> activityDetails = new ArrayList<>();

}
