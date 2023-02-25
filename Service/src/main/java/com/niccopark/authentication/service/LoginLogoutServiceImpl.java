package com.niccopark.authentication.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niccopark.dtos.FlagDTO;
import com.niccopark.entity.CurrentUserSession;
import com.niccopark.entity.Role;
import com.niccopark.exceptions.LoginException;
import com.niccopark.repository.CurrentUserSessionRepository;

@Service
public class LoginLogoutServiceImpl implements LoginLogoutService {

	@Autowired
	private CurrentUserSessionRepository sessionRepository;

	@Override
	public String getUuid(String username, Role role) {

		Optional<CurrentUserSession> opt = sessionRepository.findByUsername(username);

		if (opt.isPresent()) {
			sessionRepository.delete(opt.get());
		}
		
		String key = RandomStringUtils.randomAlphanumeric(6);

		CurrentUserSession currentUserSession = new CurrentUserSession();
		currentUserSession.setUsername(username);
		currentUserSession.setRole(role);
		currentUserSession.setUuid(key);
		currentUserSession.setSessionStart(LocalDateTime.now());
		currentUserSession.setSessionExpiry(currentUserSession.getSessionStart().plusMinutes(20));
		
		sessionRepository.save(currentUserSession);
		
		return "Logged In SuccessFully ! \nUUID : " + currentUserSession.getUuid();

	}

	@Override
	public FlagDTO validateUuid(String uuid) {
		
		Optional<CurrentUserSession> opt = sessionRepository.findById(uuid);
		
		FlagDTO dto = new FlagDTO();
		
		if(opt.isEmpty()) {
			dto.setFlag(false);
			
			return dto;
		}
		else {
			CurrentUserSession session = opt.get();
			
			if(LocalDateTime.now().until(session.getSessionExpiry(), ChronoUnit.SECONDS) > 0) {
				dto.setFlag(true);
				dto.setRole(session.getRole());
				dto.setUsername(session.getUsername());
				
				return dto;
			}
			else {
				sessionRepository.delete(session);
				
				dto.setFlag(false);
				
				return dto;
			}
		}
		
	}

	@Override
	public String logOut(String uuid) {
		
		Optional<CurrentUserSession> opt = sessionRepository.findById(uuid);
		
		if(opt.isEmpty()) {
			throw new LoginException("No User Found");
		}
		
		sessionRepository.delete(opt.get());
		
		return "Logged Out Successfully !";
		
	}

}
