package com.niccopark.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.niccopark.entity.Activity;
import com.niccopark.entity.Slot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDetailsDTO {

	private Activity activity;

	private Slot slot;

	private LocalDate date;

	private LocalDateTime bookingTime;

}
