package com.niccopark.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.niccopark.entity.Activity;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.repository.ActivityRepository;

public class ActivityServiceImpl implements ActivityService  {
@Autowired
	private  ActivityRepository activityrepo;
	@Override
	
	public Activity insertActivity(Activity activity) throws ActivityException {
		Activity act=activityrepo.findByName(activity.getName());

		
		if (act==null) {
			throw new ActivityException ("activity already exsit");
		}
		
		return activityrepo.save(activity);
		
	}

	@Override
	public Activity updateActivity(Activity activity) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity deleteActivity(int activityid) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> viewActivityofCharges(float charges) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countActivityofCharges(float charges) throws ActivityException {
		// TODO Auto-generated method stub
		return 0;
	}

}
