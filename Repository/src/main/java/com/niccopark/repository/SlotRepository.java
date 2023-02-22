package com.niccopark.repository;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer> {

	public Optional<Slot> FindByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime);

}
