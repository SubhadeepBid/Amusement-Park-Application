package com.niccopark.admin.service;

import com.niccopark.entity.Activity;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.SlotException;

public interface ActivityServiceDemo {
	
	public Activity insertActivity(Activity activity) throws ActivityException;
	
	public Activity addSlotsToActivity(Integer activityId, Integer slotId) throws ActivityException, SlotException;

}
