package com.jobtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobtracker.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByUserId(Long userId);
	
	List<Job> findByUserIdAndStatus(Long userId, String statusI);
}
