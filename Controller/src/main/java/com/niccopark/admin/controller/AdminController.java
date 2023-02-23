package com.niccopark.admin.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niccopark.admin.service.AdminService;
import com.niccopark.dtos.SlotDTO;
import com.niccopark.entity.Admin;
import com.niccopark.entity.Slot;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admins")
	public ResponseEntity<Admin> insertAdminHandler(@RequestBody Admin admin) {
		
		Admin savedAdmin = adminService.insertAdmin(admin);
		
		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/admins/slot")
	public ResponseEntity<Slot> insertSlotHandler(@RequestBody SlotDTO dto) {
		
		Slot slot = new Slot();
		
		slot.setStartTime(LocalTime.parse(dto.getStartTime()));
		slot.setEndTime(LocalTime.parse(dto.getEndTime()));
		
		Slot savedSlot = adminService.insertSlot(slot);
		
		return new ResponseEntity<>(savedSlot, HttpStatus.CREATED);
		
	}

}
