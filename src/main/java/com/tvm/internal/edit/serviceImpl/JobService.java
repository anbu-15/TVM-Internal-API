package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Job;
import com.tvm.internal.edit.repo.JobRepository;
import com.tvm.internal.edit.service.JobServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements JobServiceInterface {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job updateJob(Long id, Job jobDetails) {
        Job job = getJobById(id);
        if (job != null) {
            job.setJob(jobDetails.getJob());
            job.setDescription(jobDetails.getDescription());
            job.setStartTime(jobDetails.getStartTime());
            job.setEndTime(jobDetails.getEndTime());
            job.setUser(jobDetails.getUser());
            job.setStatus(jobDetails.getStatus());
            return jobRepository.save(job);
        }
        return null;
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
