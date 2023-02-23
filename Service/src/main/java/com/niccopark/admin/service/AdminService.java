package com.niccopark.admin.service;

import java.time.LocalDate;
import java.util.List;

import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Admin;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.SlotException;

public interface AdminService {

	public Admin insertAdmin(Admin admin) throws AdminException;
	
	public Admin validateAdmin(ValidateUserDTO dto) throws AdminException;

	public Admin updateAdminDetails(Admin admin) throws AdminException;
	
	public Admin updateAdminPassword(UpdateUserPasswordDTO dto) throws AdminException;
	
	public Admin updateAdminUsername(UpdateUserUsernameDTO dto) throws AdminException;

	public Admin deleteAdmin(Integer adminId) throws AdminException;
	
	public Slot insertSlot(Slot slot) throws SlotException;

	public List<Activity> getAllActivitiesByCustomerId(Integer customerId) throws ActivityException;

	public List<Activity> getAllActivities() throws ActivityException;

	public List<Customer> getActivitiesCustomerWise() throws ActivityException;

	public List<Activity> getActivitiesDateWise() throws ActivityException;

	public List<Ticket> getAllActivitiesForDays(Integer customerId, LocalDate fromDate, LocalDate toDate)
			throws ActivityException;

}
