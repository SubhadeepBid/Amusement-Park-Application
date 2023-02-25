package com.niccopark.admin.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.admin.service.AdminService;
import com.niccopark.authentication.service.LoginLogoutService;
import com.niccopark.dtos.ActivityDetailsDTO;
import com.niccopark.dtos.CustomerDetailsDTO;
import com.niccopark.dtos.CustomerWiseDTO;
import com.niccopark.dtos.DateWiseDTO;
import com.niccopark.dtos.SlotDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.UserUpdateDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Admin;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private LoginLogoutService loginLogoutService;
	
	@PostMapping("/admin_sign_up")
	public ResponseEntity<Admin> insertAdminHandler(@Valid @RequestBody Admin admin) {

		Admin savedAdmin = adminService.insertAdmin(admin);

		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);

	}

	@PutMapping("/update_admin_details")
	public ResponseEntity<Admin> updateAdminDetailsHandler(@Valid @RequestBody UserUpdateDTO dto, @RequestParam String adminUuid) {

		Admin updatedAdmin = adminService.updateAdminDetails(dto, adminUuid);

		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);

	}

	@PutMapping("/update_admin_password")
	public ResponseEntity<Admin> updateAdminPasswordHandler(@Valid @RequestBody UpdateUserPasswordDTO dto, @RequestParam String adminUuid) {

		Admin updatedAdmin = adminService.updateAdminPassword(dto, adminUuid);

		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);

	}

	@PutMapping("/update_admin_username")
	public ResponseEntity<Admin> updateAdminUsernameHandler(@Valid @RequestBody UpdateUserUsernameDTO dto, @RequestParam String adminUuid) {

		Admin updatedAdmin = adminService.updateAdminUsername(dto, adminUuid);

		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);

	}

	@DeleteMapping("/delete_admin/{adminId}")
	public ResponseEntity<Admin> deleteAdminHandler(@PathVariable("adminId") Integer adminId, @RequestParam String adminUuid) {

		Admin deletedAdmin = adminService.deleteAdmin(adminId, adminUuid);

		return new ResponseEntity<>(deletedAdmin, HttpStatus.OK);

	}

	@PostMapping("/add_slot")
	public ResponseEntity<Slot> insertSlotHandler(@RequestBody SlotDTO dto, @RequestParam String adminUuid) {

		Slot slot = new Slot();

		slot.setStartTime(LocalTime.parse(dto.getStartTime()));
		slot.setEndTime(LocalTime.parse(dto.getEndTime()));

		Slot savedSlot = adminService.insertSlot(slot, adminUuid);

		return new ResponseEntity<>(savedSlot, HttpStatus.CREATED);

	}

	@GetMapping("/get_all_activities/{customerId}")
	public ResponseEntity<List<Activity>> getAllActivitiesByCustomerIdHandler(
			@PathVariable("customerId") Integer customerID, @RequestParam String adminUuid) {

		List<Activity> activities = adminService.getAllActivitiesByCustomerId(customerID, adminUuid);

		return new ResponseEntity<>(activities, HttpStatus.OK);

	}

	@GetMapping("/get_all_activities")
	public ResponseEntity<List<Activity>> getAllActivitiesHandler(@RequestParam String uuid) {

		List<Activity> activities = adminService.getAllActivities(uuid);

		return new ResponseEntity<>(activities, HttpStatus.OK);

	}

	@GetMapping("/get_activities_customerwise")
	public ResponseEntity<List<CustomerWiseDTO>> getActivitiesCustomerWiseHandler(@RequestParam String adminUuid) {

		List<CustomerWiseDTO> tickets = adminService.getActivitiesCustomerWise(adminUuid);

		return new ResponseEntity<>(tickets, HttpStatus.OK);

	}

	@GetMapping("/get_activities_datewise")
	public ResponseEntity<List<DateWiseDTO>> getActivitiesDateWiseHandler(@RequestParam String adminUuid) {

		List<DateWiseDTO> dtos = adminService.getActivitiesDateWise(adminUuid);

		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}

	@GetMapping("/get_all_activities_for_days/{customerId}")
	public ResponseEntity<List<Ticket>> getAllActivitiesForDaysHandler(@PathVariable("customerId") Integer customerId,
			@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String adminUuid) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		
		LocalDate d1 = LocalDate.parse(fromDate, formatter);
		LocalDate d2 = LocalDate.parse(toDate, formatter);
		
		List<Ticket> tickets = adminService.getAllActivitiesForDays(customerId, d1, d2, adminUuid);
		
		return new ResponseEntity<>(tickets, HttpStatus.OK);

	}
	
	@GetMapping("/get_revenue")
	public ResponseEntity<Double> getTotalRevenue(@RequestParam String adminUuid) {
		
		return new ResponseEntity<>(adminService.getTotalRevenue(adminUuid), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/log_out_admin")
	public ResponseEntity<String> logOutHandler(@RequestParam String adminUuid) {
		
		return new ResponseEntity<>(loginLogoutService.logOut(adminUuid), HttpStatus.OK);
		
	}
	
	@PostMapping("/login_admin")
	public ResponseEntity<String> loginAdminHandler(@Valid @RequestBody ValidateUserDTO validateUserDTO) {
		
		return new ResponseEntity<>(adminService.loginAdmin(validateUserDTO), HttpStatus.OK);
		
	}

}
