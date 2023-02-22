package com.niccopark.admin.service;

import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Admin;
import com.niccopark.exceptions.AdminException;

public interface AdminService {

	public Admin insertAdmin(Admin admin) throws AdminException;
	
	public Admin validateAdmin(ValidateUserDTO dto) throws AdminException;

	public Admin updateAdminDetails(Admin admin) throws AdminException;
	
	public Admin updateAdminPassword(UpdateUserPasswordDTO dto) throws AdminException;
	
	public Admin updateAdminUsername(UpdateUserUsernameDTO dto) throws AdminException;

	public Admin deleteAdmin(Integer adminId) throws AdminException;

//	public List<Activity> getAllActivities(Integer customerId) throws ActivityException;
//
//	public List<Activity> getAllActivities() throws ActivityException;
//
//	public List<Activity> getActivitiesCustomerWise() throws ActivityException;
//
//	public List<Activity> getActivitiesDateWise() throws ActivityException;
//
//	public List<Activity> getAllActivitiesForDays(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate)
//			throws ActivityException;

}
