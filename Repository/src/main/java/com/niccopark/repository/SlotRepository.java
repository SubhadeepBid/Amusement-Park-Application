package com.niccopark.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.niccopark.entity.Activity;
import com.niccopark.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer> {

	public Optional<Slot> findByStartTimeAndEndTime(LocalTime startTime, LocalTime endTime);
	
	@Query("select activities from Slot where slotId=?1")
	public List<Activity> getAllActivities(Integer slotId);

}
