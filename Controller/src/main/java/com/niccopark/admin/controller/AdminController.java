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
import com.niccopark.dtos.ActivityDetailsDTO;
import com.niccopark.dtos.CustomerDetailsDTO;
import com.niccopark.dtos.CustomerWiseDTO;
import com.niccopark.dtos.DateWiseDTO;
import com.niccopark.dtos.SlotDTO;
import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Activity;
import com.niccopark.entity.Admin;
import com.niccopark.entity.Customer;
import com.niccopark.entity.Slot;
import com.niccopark.entity.Ticket;

@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add_admin")
	public ResponseEntity<Admin> insertAdminHandler(@RequestBody Admin admin) {

		Admin savedAdmin = adminService.insertAdmin(admin);

		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);

	}

	@PostMapping("/validate_admin")
	public ResponseEntity<Admin> validateAdminHandler(@RequestBody ValidateUserDTO dto) {

		Admin validatedAdmin = adminService.validateAdmin(dto);

		return new ResponseEntity<>(validatedAdmin, HttpStatus.OK);

	}

	@PutMapping("/update_admin_details")
	public ResponseEntity<Admin> updateAdminDetailsHandler(@RequestBody Admin admin) {

		Admin updatedAdmin = adminService.updateAdminDetails(admin);

		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);

	}

	@PutMapping("/update_admin_password")
	public ResponseEntity<Admin> updateAdminPasswordHandler(@RequestBody UpdateUserPasswordDTO dto) {

		Admin updatedAdmin = adminService.updateAdminPassword(dto);

		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);

	}

	@PutMapping("/update_admin_username")
	public ResponseEntity<Admin> updateAdminUsernameHandler(@RequestBody UpdateUserUsernameDTO dto) {

		Admin updatedAdmin = adminService.updateAdminUsername(dto);

		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);

	}

	@DeleteMapping("/delete_admin/{adminId}")
	public ResponseEntity<Admin> deleteAdminHandler(@PathVariable("adminId") Integer adminId) {

		Admin deletedAdmin = adminService.deleteAdmin(adminId);

		return new ResponseEntity<>(deletedAdmin, HttpStatus.OK);

	}

	@PostMapping("/add_slot")
	public ResponseEntity<Slot> insertSlotHandler(@RequestBody SlotDTO dto) {

		Slot slot = new Slot();

		slot.setStartTime(LocalTime.parse(dto.getStartTime()));
		slot.setEndTime(LocalTime.parse(dto.getEndTime()));

		Slot savedSlot = adminService.insertSlot(slot);

		return new ResponseEntity<>(savedSlot, HttpStatus.CREATED);

	}

	@GetMapping("/get_all_activities/{customerId}")
	public ResponseEntity<List<Activity>> getAllActivitiesByCustomerIdHandler(
			@PathVariable("customerId") Integer customerID) {

		List<Activity> activities = adminService.getAllActivitiesByCustomerId(customerID);

		return new ResponseEntity<>(activities, HttpStatus.OK);

	}

	@GetMapping("/get_all_activities")
	public ResponseEntity<List<Activity>> getAllActivitiesHandler() {

		List<Activity> activities = adminService.getAllActivities();

		return new ResponseEntity<>(activities, HttpStatus.OK);

	}

	@GetMapping("/get_activities_customerwise")
	public ResponseEntity<List<CustomerWiseDTO>> getActivitiesCustomerWiseHandler() {

		List<CustomerWiseDTO> tickets = adminService.getActivitiesCustomerWise();

		return new ResponseEntity<>(tickets, HttpStatus.OK);

	}

	@GetMapping("/get_activities_datewise")
	public ResponseEntity<List<DateWiseDTO>> getActivitiesDateWiseHandler() {

		List<DateWiseDTO> dtos = adminService.getActivitiesDateWise();

		return new ResponseEntity<>(dtos, HttpStatus.OK);

	}

	@GetMapping("/get_all_activities_for_days/{customerId}")
	public ResponseEntity<List<Ticket>> getAllActivitiesForDaysHandler(@PathVariable("customerId") Integer customerId,
			@RequestParam String fromDate, @RequestParam String toDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		
		LocalDate d1 = LocalDate.parse(fromDate, formatter);
		LocalDate d2 = LocalDate.parse(toDate, formatter);
		
		List<Ticket> tickets = adminService.getAllActivitiesForDays(customerId, d1, d2);
		
		return new ResponseEntity<>(tickets, HttpStatus.OK);

	}
	
	@GetMapping("/get_revenue")
	public ResponseEntity<Double> getTotalRevenue() {
		
		return new ResponseEntity<>(adminService.getTotalRevenue(), HttpStatus.OK);
		
	}

}
