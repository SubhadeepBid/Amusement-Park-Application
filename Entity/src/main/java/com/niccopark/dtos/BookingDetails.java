package com.niccopark.dtos;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {

	private Integer customerId;

	private String activityName;

	private LocalTime startTime;

	private LocalTime endTime;

}
