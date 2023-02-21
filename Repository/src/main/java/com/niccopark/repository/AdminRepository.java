package com.niccopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
