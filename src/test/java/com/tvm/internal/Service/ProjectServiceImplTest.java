package com.tvm.internal.Service;

import com.tvm.internal.edit.model.Project;
import com.tvm.internal.edit.repo.ProjectRepository;
import com.tvm.internal.edit.service.ProjectService;
import com.tvm.internal.edit.serviceImpl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project();
        project.setId(1L);
        project.setProjectName("Test Project");
        project.setClientName("Test Client");
        project.setDomain("Test Domain");
    }

    @Test
    void getAllProjects() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(project));

        List<Project> projects = projectService.getAllProjects();

        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals("Test Project", projects.get(0).getProjectName());
    }

    @Test
    void getProjectById_existingId() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> foundProject = projectService.getProjectById(1L);

        assertTrue(foundProject.isPresent());
        assertEquals("Test Project", foundProject.get().getProjectName());
    }

    @Test
    void getProjectById_nonExistingId() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Project> foundProject = projectService.getProjectById(1L);

        assertFalse(foundProject.isPresent());
    }

    @Test
    void saveProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project savedProject = projectService.saveProject(project);

        assertNotNull(savedProject);
        assertEquals("Test Project", savedProject.getProjectName());
    }

    @Test
    void updateProject_existingId() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.updateProject(1L, project);

        assertNotNull(updatedProject);
        assertEquals("Test Project", updatedProject.getProjectName());
        verify(projectRepository).save(project);
    }

    @Test
    void updateProject_nonExistingId() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        Project updatedProject = projectService.updateProject(1L, project);

        assertNull(updatedProject);
        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void deleteProject_existingId() {
        doNothing().when(projectRepository).deleteById(1L);

        projectService.deleteProject(1L);

        verify(projectRepository).deleteById(1L);
    }


}
