package com.niccopark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.CurrentUserSession;

public interface CurrentUserSessionRepository extends JpaRepository<CurrentUserSession, String> {
	
	public Optional<CurrentUserSession> findByUuid(String uuid);

}
