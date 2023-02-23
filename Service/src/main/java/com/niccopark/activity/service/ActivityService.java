package com.niccopark.activity.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.niccopark.entity.Activity;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.SlotException;

public interface ActivityService {

	public Activity insertActivity(Activity activity) throws ActivityException;

	public Activity updateActivity(Activity activity) throws ActivityException;

	public Activity deleteActivity(Integer activityId) throws ActivityException;

	public List<Activity> viewActivitiesOfCharge(Float charges) throws ActivityException;

<<<<<<< HEAD
	public Integer countActivitiesOfCharges(Float charges) throws ActivityException;

=======
	public int countActivityofCharges(float charges) throws ActivityException;
	
>>>>>>> branch 'master' of https://github.com/SubhadeepBid/agreeable-development-7620.git
	public Activity addSlotsToActivity(Integer activityId, Integer slotId) throws ActivityException, SlotException;

}
