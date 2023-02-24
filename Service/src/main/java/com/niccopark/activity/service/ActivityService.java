package com.niccopark.activity.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.niccopark.entity.Activity;
import com.niccopark.entity.Slot;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.SlotException;

public interface ActivityService {

	public Activity insertActivity(Activity activity) throws ActivityException;

	public Activity updateActivity(Activity activity) throws ActivityException;

	public Activity deleteActivity(Integer activityId) throws ActivityException;

	public List<Activity> viewActivitiesOfCharge(Float charges) throws ActivityException;

	public Integer countActivitiesOfCharges(Float charges) throws ActivityException;

	public Activity addSlotsToActivity(Integer activityId, Integer slotId) throws ActivityException, SlotException;
	
	public List<Activity> getAllActivitiesBetweenRange(Float fromCharges, Float toCharges) throws ActivityException;

	public List<Slot> getAllSlotsForActivity(String activityName) throws ActivityException, SlotException;
	
	public List<Activity> getAllActivitiesFromSlot(Integer slotId) throws ActivityException, SlotException;
	
}
