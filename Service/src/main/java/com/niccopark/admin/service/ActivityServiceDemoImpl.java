package com.niccopark.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.niccopark.entity.Activity;
import com.niccopark.entity.Slot;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.repository.ActivityRepository;
import com.niccopark.repository.SlotRepository;

public class ActivityServiceDemoImpl implements ActivityServiceDemo {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private SlotRepository slotRepository;

	@Override
	public Activity insertActivity(Activity activity) throws ActivityException {
		return activityRepository.save(activity);
	}

	@Override
	public Activity addSlotsToActivity(Integer activityId, Integer slotId) throws ActivityException, SlotException {
		
		Optional<Activity> opt = activityRepository.findById(activityId);
		
		if(opt.isEmpty()) {
			throw new ActivityException("Activity Not Found");
		}
		
		Activity activity = opt.get();
		
		Optional<Slot> opt1 = slotRepository.findById(slotId);
		
		if(opt1.isEmpty()) {
			throw new SlotException("Slot Not Found");
		}
		
		if(activity.getSlots().contains(opt1.get())) {
			throw new SlotException("Slot Already Added");
		}
		
		activity.getSlots().add(opt1.get());
		
		return activityRepository.save(activity);
	}
 
}
