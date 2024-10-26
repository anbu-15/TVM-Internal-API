package com.tvm.internal.edit.service;


import com.tvm.internal.edit.model.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAllProjects();
    Optional<Project> getProjectById(Long id);
    Project saveProject(Project project);
    Project updateProject(Long id, Project project);
    void deleteProject(Long id);
}