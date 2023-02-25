package com.niccopark.authentication.service;

import com.niccopark.dtos.FlagDTO;
import com.niccopark.entity.Role;

public interface LoginLogoutService {
	
	public String getUuid(String username, Role role);
	
	public FlagDTO validateUuid(String uuid);
	
	public String logOut(String uuid);

}
