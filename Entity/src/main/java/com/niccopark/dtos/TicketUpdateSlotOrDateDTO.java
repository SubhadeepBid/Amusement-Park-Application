package com.niccopark.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketUpdateSlotOrDateDTO {

	private Integer slotId;

	private LocalDate date;

}
