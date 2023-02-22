package com.niccopark.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	
	private String customerName;
	
	private String activityName;
	
	private Float activityCharge;
	
	private String mobileNumber;
	
}
