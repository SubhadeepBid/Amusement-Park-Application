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
	private ActivityRepository activityrepo;
	@Autowired
	private SlotRepository slotRepository;

	@Override

	public Activity insertActivity(Activity activity) throws ActivityException {
		Optional<Activity> act = activityrepo.findByName(activity.getName());

		if (act.isPresent()) {
			throw new ActivityException("activity already exsit");
		}

		return activityrepo.save(activity);

	}

	@Override
	public Activity updateActivity(Activity activity) throws ActivityException {
		Optional<Activity> activity2 = activityrepo.findById(activity.getActivityId());
		if (activity2 == null)
			throw new ActivityException("Activity with this id " + activity.getActivityId() + "available");
		activityrepo.save(activity);
		return activity;
	}

	@Override
	public Activity deleteActivity(int activityid) throws ActivityException {
		Optional<Activity> opt = activityrepo.findById(activityid);

		if (opt == null)
			throw new ActivityException("Activity with this id " + activityid + "available");
		activityrepo.delete(opt.get());
		return opt.get();
	}

	@Override
	public List<Activity> viewActivityofCharges(float charges) throws ActivityException {
		List<Activity> al = activityrepo.findByCharges(charges);
		if (al.isEmpty())
			throw new ActivityException("Data not found");
		return al;
	}

	@Override
	public int countActivityofCharges(float charges) throws ActivityException {
		List<Activity> al = activityrepo.findByCharges(charges);
		if (al.isEmpty())
			throw new ActivityException("Data not found");
		return al.size();
	}

	@Override
	public Activity addSlotsToActivity(Integer activityId, Integer slotId) throws ActivityException, SlotException {

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

}
