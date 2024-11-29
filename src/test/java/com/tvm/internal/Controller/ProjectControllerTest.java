package com.tvm.internal.Controller;

import com.tvm.internal.edit.controller.ProjectController;
import com.tvm.internal.edit.model.Project;
import com.tvm.internal.edit.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        project = new Project();
        project.setId(1L);
        project.setProjectName("Project Alpha");
        project.setClientName("Client X");
        project.setDomain("Domain Y");
        project.setStartDate(new Date());
        project.setVoice("Voice Example");
        project.setVoiceStartDate(new Date());
        project.setVoiceEndDate(new Date());
        project.setCoding("Coding Example");
        project.setCodingStartDate(new Date());
        project.setCodingEndDate(new Date());
        project.setProjectStatus("In Progress");
        project.setAsset(Arrays.asList("Asset 1", "Asset 2"));
    }

    @Test
    void testGetAllProjects() {
        when(projectService.getAllProjects()).thenReturn(Arrays.asList(project));

        List<Project> projects = projectController.getAllProjects();

        assertNotNull(projects);
        assertEquals(1, projects.size());
        assertEquals("Project Alpha", projects.get(0).getProjectName());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void testGetProjectById() {
        when(projectService.getProjectById(1L)).thenReturn(Optional.of(project));

        ResponseEntity<Project> response = projectController.getProjectById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Project Alpha", response.getBody().getProjectName());
        verify(projectService, times(1)).getProjectById(1L);
    }

    @Test
    void testGetProjectById_NotFound() {
        when(projectService.getProjectById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Project> response = projectController.getProjectById(2L);

        assertEquals(404, response.getStatusCodeValue());
        verify(projectService, times(1)).getProjectById(2L);
    }

    @Test
    void testCreateProject() {
        when(projectService.saveProject(any(Project.class))).thenReturn(project);

        Project createdProject = projectController.createProject(project);

        assertNotNull(createdProject);
        assertEquals("Project Alpha", createdProject.getProjectName());
        verify(projectService, times(1)).saveProject(any(Project.class));
    }

    @Test
    void testUpdateProject() {
        when(projectService.updateProject(eq(1L), any(Project.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.updateProject(1L, project);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Project Alpha", response.getBody().getProjectName());
        verify(projectService, times(1)).updateProject(eq(1L), any(Project.class));
    }

    @Test
    void testUpdateProject_NotFound() {
        when(projectService.updateProject(eq(1L), any(Project.class))).thenReturn(null);

        ResponseEntity<Project> response = projectController.updateProject(1L, project);

        assertEquals(404, response.getStatusCodeValue());
        verify(projectService, times(1)).updateProject(eq(1L), any(Project.class));
    }

    @Test
    void testDeleteProject() {
        doNothing().when(projectService).deleteProject(1L);

        ResponseEntity<Void> response = projectController.deleteProject(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(projectService, times(1)).deleteProject(1L);
    }
}

