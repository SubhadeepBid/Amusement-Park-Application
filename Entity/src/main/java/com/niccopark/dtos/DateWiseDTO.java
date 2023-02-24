package com.niccopark.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateWiseDTO {
	
	private LocalDate date;
	
	private List<ActivityDetailsDateWiseDTO> activityDetails = new ArrayList<>();

}
