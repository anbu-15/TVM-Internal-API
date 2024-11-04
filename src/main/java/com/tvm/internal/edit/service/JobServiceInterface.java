package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Job;
import java.util.List;

public interface JobServiceInterface {
    Job createJob(Job job);
    List<Job> getAllJobs();
    Job getJobById(Long id);
    Job updateJob(Long id, Job jobDetails);
    void deleteJob(Long id);
}
