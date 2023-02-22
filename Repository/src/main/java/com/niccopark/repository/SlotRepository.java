package com.niccopark.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
	
	
}
