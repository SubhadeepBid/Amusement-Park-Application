package com.niccopark.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketUpdateSlotOrDateDTO {

	private Integer slotId;

	@FutureOrPresent(message = "Date shuld be in future or present")
	private LocalDate date;

}
