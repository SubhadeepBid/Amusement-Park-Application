package com.niccopark.dtos;


import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {

	private Integer customerId;

	private Integer activityId;

	private Integer slotId;
	
	@FutureOrPresent(message = "Message should be in present or future")
	private LocalDate date;

}
