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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.activity.service.ActivityService;
import com.niccopark.dtos.UpdateActivityDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Slot;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("/add_activity")
	public ResponseEntity<Activity> insertActivityHandler(@Valid @RequestBody Activity activity, @RequestParam String adminUuid) {

		Activity savedActivity = activityService.insertActivity(activity, adminUuid);

		return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);

	}

	@PutMapping("/update_activity/{activityId}")
	public ResponseEntity<Activity> updateActivityHandler(@Valid @RequestBody UpdateActivityDTO dto, @PathVariable("activityId") Integer activityId, @RequestParam String adminUuid) {

		Activity updatedActivity = activityService.updateActivity(dto, activityId, adminUuid);

		return new ResponseEntity<>(updatedActivity, HttpStatus.OK);

	}

	@DeleteMapping("/delete_activity/{activityId}")
	public ResponseEntity<Activity> deleteActivityHandler(@PathVariable("activityId") Integer activityId, @RequestParam String adminUuid) {

		Activity deletedActivity = activityService.deleteActivity(activityId, adminUuid);

		return new ResponseEntity<>(deletedActivity, HttpStatus.OK);

	}

	@GetMapping("/view_activities_of_charges/{charges}")
	public ResponseEntity<List<Activity>> viewActivitiesOfChargeHandler(@PathVariable("charges") Float charges, @RequestParam String uuid) {

		List<Activity> activities = activityService.viewActivitiesOfCharge(charges, uuid);

		return new ResponseEntity<>(activities, HttpStatus.OK);

	}

	@GetMapping("/count_activities_of_charges/{charges}")
	public ResponseEntity<Integer> countActivityofChargesHandler(@PathVariable("charges") Float charges, @RequestParam String uuid) {

		Integer count = activityService.countActivitiesOfCharges(charges, uuid);

		return new ResponseEntity<>(count, HttpStatus.OK);

	}

	@PutMapping("/add_slots_to_activity/{activityId}/{slotId}")
	public ResponseEntity<Activity> addSlotsToActivityHandler(@PathVariable("activityId") Integer activityId,
			@PathVariable("slotId") Integer slotId, @RequestParam String adminUuid) {

		Activity updatedActivity = activityService.addSlotsToActivity(activityId, slotId, adminUuid);

		return new ResponseEntity<>(updatedActivity, HttpStatus.OK);

	}
	
	@GetMapping("/get_activities_between_range")
	public ResponseEntity<List<Activity>> getAllActivitiesBetweenRange(@RequestParam Float fromCharges, @RequestParam Float toCharges, @RequestParam String uuid) {
		
		return new ResponseEntity<>(activityService.getAllActivitiesBetweenRange(fromCharges, toCharges, uuid), HttpStatus.OK);
		
	}
	
	@GetMapping("/get_all_slots_for_activity/{activityName}")
	public ResponseEntity<List<Slot>> getAllSlotsForActivity(@PathVariable("activityName") String activityName, @RequestParam String uuid) {
		
		return new ResponseEntity<>(activityService.getAllSlotsForActivity(activityName, uuid), HttpStatus.OK);
		
	}
	
	@GetMapping("/get_all_activities_for_slot/{slotId}")
	public ResponseEntity<List<Activity>> getAllActivitiesFromSlot(@PathVariable("slotId") Integer slotId, @RequestParam String uuid) {
		
		return new ResponseEntity<>(activityService.getAllActivitiesFromSlot(slotId, uuid), HttpStatus.OK);
		
	}
 
}
