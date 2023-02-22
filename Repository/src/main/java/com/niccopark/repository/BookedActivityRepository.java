package com.niccopark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niccopark.entity.BookedActivity;

public interface BookedActivityRepository extends JpaRepository<BookedActivity, Integer> {

}
