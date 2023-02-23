package com.niccopark.activity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.entity.Activity;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityRepository activityrepo;

	@Override

	public Activity insertActivity(Activity activity) throws ActivityException {
		Activity act = activityrepo.findByName(activity.getName());

		if (act == null) {
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

}
