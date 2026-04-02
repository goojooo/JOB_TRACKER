package com.jobtracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.entity.Job;
import com.jobtracker.service.JobService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobController {

	private JobService jobService;
	
    // Constructor Injection (BEST PRACTICE)
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
	
	// ADD JOB
	@PostMapping("/{userId}")
	public Job addJob(@RequestBody Job job,
			@PathVariable Long userId) {
		return jobService.addJob(job, userId);
	}
	
	// GET JOBS
	@GetMapping("/{userId}")
	public List<Job> getJobs(@PathVariable Long userId){
		return jobService.getJobsByUser(userId);
	}
	
	@PutMapping("/{jobId}")
	public Job updateStatus(@PathVariable Long jobId,
			@RequestParam String status) {
		return jobService.updateJobStatus(jobId, status);
	}

	@DeleteMapping("/{jobId}")
	public String deleteJob(@PathVariable Long jobId) {
		jobService.deleteJob(jobId);
		return "Job deleted successfully...";
	}
	
	@GetMapping("/{userId}/filter")
	public List<Job> filterJobs(@PathVariable Long userId,
			@RequestParam String status) {
		return jobService.grtJobsByStatus(userId, status);
	}
}
