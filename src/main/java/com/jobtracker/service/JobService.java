package com.jobtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobtracker.entity.Job;
import com.jobtracker.entity.JobStatus;
import com.jobtracker.entity.User;
import com.jobtracker.repository.JobRepository;
import com.jobtracker.repository.UserRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// ADD JOB
	public Job addJob(Job job, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		job.setUser(user);
		return jobRepository.save(job);
	}
	
	// GET ALL JOBS FOR USER
	public List<Job> getJobsByUser(Long userId){
		return jobRepository.findByUserId(userId);
	}
	
	public Job updateJobStatus(Long jobId, String status) {
		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new RuntimeException("Job not found..."));
		
		job.setStatus(status);
		return jobRepository.save(job);
	}
	
	public void deleteJob(Long jobId) {
		jobRepository.deleteById(jobId);
	}
	
	public List<Job> grtJobsByStatus(Long userId, String status) {
		return jobRepository.findByUserIdAndStatus(userId, status);
	}
}
