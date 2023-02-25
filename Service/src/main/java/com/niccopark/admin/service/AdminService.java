package com.niccopark.admin.service;

import java.time.LocalDate;
import java.util.List;

import com.niccopark.dtos.CustomerWiseDTO;
import com.niccopark.dtos.DateWiseDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Admin;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;
import com.niccopark.exceptions.ActivityException;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.SlotException;
import com.niccopark.exceptions.TicketException;
import com.niccopark.exceptions.UserException;

public interface AdminService {

	public Admin insertAdmin(Admin admin) throws AdminException;
	
	public Admin validateAdmin(ValidateUserDTO dto) throws AdminException;

	public Admin updateAdminDetails(UserUpdateDTO dto, String uuid) throws AdminException;
	
	public Admin updateAdminPassword(UpdateUserPasswordDTO dto, String uuid) throws AdminException;
	
	public Admin updateAdminUsername(UpdateUserUsernameDTO dto, String uuid) throws AdminException;

	public Admin deleteAdmin(Integer adminId, String uuid) throws AdminException;
	
	public Slot insertSlot(Slot slot, String uuid) throws AdminException, SlotException;

	public List<Activity> getAllActivitiesByCustomerId(Integer customerId, String uuid) throws AdminException, ActivityException;

	public List<Activity> getAllActivities(String uuid) throws UserException, ActivityException;

	public List<CustomerWiseDTO> getActivitiesCustomerWise(String uuid) throws AdminException, ActivityException;

	public List<DateWiseDTO> getActivitiesDateWise(String uuid) throws AdminException, ActivityException;

	public List<Ticket> getAllActivitiesForDays(Integer customerId, LocalDate fromDate, LocalDate toDate, String uuid)
			throws AdminException, ActivityException;
	
	public Double getTotalRevenue(String uuid) throws AdminException, TicketException;

	public String loginAdmin(ValidateUserDTO dto) throws AdminException;
	
	public String getValidatedUsernameForAdmin(String uuid) throws AdminException;

}
