package com.niccopark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	
	public Activity findByName(String name);
	
	public List<Activity> findByCharges(float charge);
}
