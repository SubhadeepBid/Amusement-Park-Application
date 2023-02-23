package com.niccopark.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.activity.service.ActivityService;
import com.niccopark.admin.service.ActivityServiceDemo;
import com.niccopark.entity.Activity;

@RestController
public class ActivityController {

	@Autowired
	private ActivityService activi;
	
	@Autowired
	private ActivityServiceDemo activityServiceDemo;
	
	@PostMapping("/activity")
	public ResponseEntity<Activity> createActivityHandler(@RequestBody Activity activity) {

		return new ResponseEntity<>(activi.insertActivity(activity), HttpStatus.CREATED);

	}

	@PostMapping("/updateActivity")
	public ResponseEntity<Activity> updateActivity(@RequestBody Activity activity) {
		return new ResponseEntity<>(activi.updateActivity(activity), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteActivity/{id}")
	public ResponseEntity<Activity> deleteActivity(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(activi.deleteActivity(id), HttpStatus.OK);
	}

	@GetMapping("/viewActivityOfCharges/{charges}")
	public ResponseEntity<List<Activity>> viewActivityOfCharges(@PathVariable("charges") Integer charges) {
		return new ResponseEntity<List<Activity>>(activi.viewActivityofCharges(charges), HttpStatus.ACCEPTED);
	}

	@GetMapping("/countActivityofCharges/{count}")
	public ResponseEntity<Integer> countActivityofCharges(@PathVariable Integer count) {
		return new ResponseEntity<>(activi.countActivityofCharges(count), HttpStatus.OK);
	}
	
	
	@PostMapping("/activity/{aid}/{sid}")
	public ResponseEntity<Activity> addSlotsToActivityHandler(@PathVariable("aid") Integer activityId, @PathVariable("sid") Integer slotId){
		
		return new ResponseEntity<>(activi.addSlotsToActivity(activityId, slotId), HttpStatus.CREATED);
		
	}

}
