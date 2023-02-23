package com.niccopark.activity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.entity.Activity;
import com.niccopark.entity.Slot;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.repository.ActivityRepository;
import com.niccopark.repository.SlotRepository;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
<<<<<<< HEAD
	private ActivityRepository activityRepository;

=======
	private ActivityRepository activityrepo;
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
	@Autowired
	private SlotRepository slotRepository;

	@Override
	public Activity insertActivity(Activity activity) throws ActivityException {
<<<<<<< HEAD
		
		Optional<Activity> opt = activityRepository.findByName(activity.getName());
		
		if(opt.isPresent()) {
			throw new ActivityException("Activity With Name " + activity.getName() + " Is Already Present");
=======
		Optional<Activity> act = activityrepo.findByName(activity.getName());

		if (act.isPresent()) {
			throw new ActivityException("activity already exsit");
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
		}
<<<<<<< HEAD
		
		return activityRepository.save(activity);
		
=======

		return activityrepo.save(activity);

>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
	}

	@Override
	public Activity updateActivity(Activity activity) throws ActivityException {
		
		Optional<Activity> opt = activityRepository.findById(activity.getActivityId());
		
		if(opt.isEmpty()) {
			throw new ActivityException("Activity Not Found");
		}
		
		Activity existingActivity = opt.get();
		
		if(activity.getCharges() != null) {
			existingActivity.setCharges(activity.getCharges());
		}
		if(activity.getDescription() != null) {
			existingActivity.setDescription(activity.getDescription());
		}
		if(activity.getName() != null) {
			existingActivity.setName(activity.getName());
		}
		
		return activityRepository.save(existingActivity);
		
	}

	@Override
	public Activity deleteActivity(Integer activityId) throws ActivityException {

		Optional<Activity> opt = activityRepository.findById(activityId);
		
		if(opt.isEmpty()) {
			throw new ActivityException("Activity Not Found");
		}
		
		Activity existingActivity = opt.get();
		
		activityRepository.delete(existingActivity);
		
		return existingActivity;
		
	}

	@Override
	public List<Activity> viewActivitiesOfCharge(Float charges) throws ActivityException {
		
		List<Activity> activities = activityRepository.findByCharges(charges);
		
		if(activities.isEmpty()) {
			throw new ActivityException("No Activities Found");
		}
		
		return activities;
		
	}

	@Override
	public Integer countActivitiesOfCharges(Float charges) throws ActivityException {
		
		Integer count = activityRepository.getCountOfActivitiesOfCharges(charges);
		
		if(count == null || count == 0) {
			throw new ActivityException("No Activities Found");
		}
		
		return count;
		
	}

	@Override
	public Activity addSlotsToActivity(Integer activityId, Integer slotId) throws ActivityException, SlotException {
<<<<<<< HEAD
		
		Optional<Activity> opt = activityRepository.findById(activityId);
		
		if(opt.isEmpty()) {
			throw new ActivityException("Activity Not Found");
		}
		
		Activity activity = opt.get();
		
		Optional<Slot> opt1 = slotRepository.findById(slotId);
		
		if(opt1.isEmpty()) {
			throw new SlotException("Slot Not Found");
		}
		
		Slot slot = opt1.get();
		
		if(activity.getSlots().contains(slot)) {
			throw new SlotException("Slot Already Added");
		}
		
		activity.getSlots().add(slot);
		
		slot.getActivities().add(activity);
		
		return activityRepository.save(activity);
		
	}
    
=======

		Optional<Activity> opt = activityrepo.findById(activityId);

		if (opt.isEmpty()) {
			throw new ActivityException("Activity Not Found");
		}

		Activity activity = opt.get();

		Optional<Slot> opt1 = slotRepository.findById(slotId);

		if (opt1.isEmpty()) {
			throw new SlotException("Slot Not Found");
		}

		if (activity.getSlots().contains(opt1.get())) {
			throw new SlotException("Slot Already Added");
		}

		activity.getSlots().add(opt1.get());

//		opt1.get().getActivities().add(activity);

		return activityrepo.save(activity);
	}

>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
}
