package com.niccopark.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
	
	private Integer customerId;
	
	private String activityName;
	
}
