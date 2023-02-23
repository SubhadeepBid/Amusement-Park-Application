package com.niccopark.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.activity.service.ActivityService;
import com.niccopark.entity.Activity;

@RestController
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
<<<<<<< HEAD
	private ActivityService activityService;

	@PostMapping("/add_activity")
	public ResponseEntity<Activity> insertActivityHandler(@RequestBody Activity activity) {
		
		Activity savedActivity = activityService.insertActivity(activity);
=======
	private ActivityService activi;
	
	@Autowired
	private ActivityServiceDemo activityServiceDemo;
	
	@PostMapping("/activity")
	public ResponseEntity<Activity> createActivityHandler(@RequestBody Activity activity) {
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git

		return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);

	}

	@PutMapping("/update_activity")
	public ResponseEntity<Activity> updateActivityHandler(@RequestBody Activity activity) {
		
		Activity updatedActivity = activityService.updateActivity(activity);
		
		return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
		
	}

	@DeleteMapping("/delete_activity/{activityId}")
	public ResponseEntity<Activity> deleteActivityHandler(@PathVariable("activityId") Integer activityId) {
		
		Activity deletedActivity = activityService.deleteActivity(activityId);
		
		return new ResponseEntity<>(deletedActivity, HttpStatus.OK);
		
	}

	@GetMapping("/view_activities_of_charges/{charges}")
	public ResponseEntity<List<Activity>> viewActivitiesOfChargeHandler(@PathVariable("charges") Float charges) {
		
		List<Activity> activities = activityService.viewActivitiesOfCharge(charges);
		
		return new ResponseEntity<>(activities, HttpStatus.OK);
		
	}

	@GetMapping("/count_activities_of_charges/{charges}")
	public ResponseEntity<Integer> countActivityofChargesHandler(@PathVariable("charges") Float charges) {
		
		Integer count = activityService.countActivitiesOfCharges(charges);
		
		return new ResponseEntity<>(count, HttpStatus.OK);
		
	}
	
<<<<<<< HEAD
	@PutMapping("/add_slots_to_activity/{activityId}/{slotId}")
	public ResponseEntity<Activity> addSlotsToActivityHandler(@PathVariable("activityId") Integer activityId, @PathVariable("slotId") Integer slotId) {
		
		Activity updatedActivity = activityService.addSlotsToActivity(activityId, slotId);
		
		return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
=======
	
	@PostMapping("/activity/{aid}/{sid}")
	public ResponseEntity<Activity> addSlotsToActivityHandler(@PathVariable("aid") Integer activityId, @PathVariable("sid") Integer slotId){
		
		return new ResponseEntity<>(activi.addSlotsToActivity(activityId, slotId), HttpStatus.CREATED);
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
		
	}

}
