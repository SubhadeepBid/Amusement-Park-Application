package com.niccopark.repository;


import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
	
	public Slot findByUsernameAndPassword(LocalTime startTime, LocalTime endTime);
	
}
