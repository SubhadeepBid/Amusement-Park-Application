package com.niccopark.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.UpdateUserPasswordDTO;
import com.niccopark.dtos.UpdateUserUsernameDTO;
import com.niccopark.dtos.ValidateUserDTO;
import com.niccopark.entity.Admin;
import com.niccopark.exceptions.AdminException;
import com.niccopark.exceptions.CustomerException;
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

	@Override
	public Admin updateAdminDetails(Admin admin) throws AdminException {
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(admin.getUsername(), admin.getPassword()));
		
		if(admin.getAddress() != null) {
			savedAdmin.setAddress(admin.getAddress());
		}
		if(admin.getMobileNumber() != null) {
			savedAdmin.setMobileNumber(admin.getMobileNumber());
		}
		if(admin.getEmail() != null) {
			savedAdmin.setEmail(admin.getEmail());
		}
		
		return adminRepository.save(savedAdmin);
		
	}

	@Override
	public Admin updateAdminPassword(UpdateUserPasswordDTO dto) throws AdminException {
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(dto.getUsername(), dto.getOldPassword()));
		
		if(dto.getNewPassword() != null) {
			savedAdmin.setPassword(dto.getNewPassword());
		}
		
		return adminRepository.save(savedAdmin);
		
	}

	@Override
	public Admin updateAdminUsername(UpdateUserUsernameDTO dto) throws AdminException {
		
		Admin savedAdmin = validateAdmin(new ValidateUserDTO(dto.getOldUsername(), dto.getPassword()));
		
		if(savedAdmin != null && dto.getNewUsername() != null) {
			if(adminRepository.findByUsername(dto.getOldUsername()).isEmpty()) {
				savedAdmin.setUsername(dto.getNewUsername());
				
				return adminRepository.save(savedAdmin);
			}
			else {
				throw new AdminException("Admin Already Exist With Username " + dto.getNewUsername());
			}
		}
		else {
			throw new AdminException("Invalid Username or Password...");
		}
		
	}

	@Override
	public Admin deleteAdmin(Integer adminId) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findById(adminId);
		
		if(opt.isEmpty()) {
			throw new AdminException("Admin Not Found With Admin ID : " + adminId);
		}
		
		Admin savedAdmin = opt.get();
		
		adminRepository.delete(savedAdmin);
		
		return savedAdmin;
		
	}

}
