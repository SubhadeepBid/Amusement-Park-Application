package com.niccopark.activity.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.niccopark.entity.Activity;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.SlotException;

public interface ActivityService  {

	
	public Activity insertActivity(Activity activity) throws ActivityException;

	public Activity updateActivity(Activity activity) throws ActivityException;

	public Activity deleteActivity(int activityid) throws ActivityException;

	public List<Activity> viewActivityofCharges(float charges) throws ActivityException;

	public int countActivityofCharges(float charges) throws ActivityException;
	
	public Activity addSlotsToActivity(Integer activityId, Integer slotId) throws ActivityException, SlotException;

}
