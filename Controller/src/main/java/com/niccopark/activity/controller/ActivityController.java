package com.niccopark.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.admin.service.ActivityServiceDemo;
import com.niccopark.entity.Activity;

@RestController
public class ActivityController {
	
	@Autowired
	private ActivityServiceDemo activi;
	
	@PostMapping("/activity")
	public ResponseEntity<Activity> createActivityHandler(@RequestBody Activity activity){
		
		
		return new ResponseEntity<>(activi.insertActivity(activity), HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/activity/{aid}/{sid}")
	public ResponseEntity<Activity> addSlotsToActivityHandler(@PathVariable("aid") Integer activityId, @PathVariable("sid") Integer slotId){
		
		return new ResponseEntity<>(activi.addSlotsToActivity(activityId, slotId), HttpStatus.CREATED);
		
	}
	
}
