package com.niccopark.activity.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.niccopark.dtos.UpdateActivityDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Slot;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.exceptions.UserException;

public interface ActivityService {

	public Activity insertActivity(Activity activity, String uuid) throws AdminException, ActivityException;

	public Activity updateActivity(UpdateActivityDTO dto, Integer activityId, String uuid) throws AdminException, ActivityException;

	public Activity deleteActivity(Integer activityId, String uuid) throws AdminException, ActivityException;
	
	public List<Activity> viewActivitiesOfCharge(Float charges, String uuid) throws UserException, ActivityException;

	public Integer countActivitiesOfCharges(Float charges, String uuid) throws UserException, ActivityException;

	public Activity addSlotsToActivity(Integer activityId, Integer slotId, String uuid) throws AdminException, ActivityException, SlotException;
	
	public List<Activity> getAllActivitiesBetweenRange(Float fromCharges, Float toCharges, String uuid) throws UserException, ActivityException;

	public List<Slot> getAllSlotsForActivity(String activityName, String uuid) throws UserException, ActivityException, SlotException;
	
	public List<Activity> getAllActivitiesFromSlot(Integer slotId, String uuid) throws UserException, ActivityException, SlotException;
	
	public String getValidatedUsername(String uuid) throws UserException;
	
}
