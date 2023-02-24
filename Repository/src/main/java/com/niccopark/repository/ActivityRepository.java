package com.niccopark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.niccopark.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	public Optional<Activity> findByName(String name);

	public List<Activity> findByCharges(Float charges);
	
	public List<Activity> findByChargesBetween(Float fromCharge, Float toCharge);
	
	@Query("select COUNT(activityId) from Activity WHERE charges=?1")
	public Integer getCountOfActivitiesOfCharges(Float charges);

}
