package com.niccopark.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Admin;
import com.niccopark.exceptions.AdminException;
import com.niccopark.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin insertAdmin(Admin admin) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findByUsername(admin.getUsername());
		
		if(opt.isPresent()) {
			throw new AdminException("Admin With Username " + admin.getUsername() + " Is Already Present...");
		}
		
		return adminRepository.save(admin);
		
	}
	
	@Override
	public Admin validateAdmin(ValidateUserDTO dto) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
		
		if(opt.isEmpty()) {
			throw new AdminException("Wrong Username Or Password Entered...");
		}
		
		return opt.get();
		
	}

}
