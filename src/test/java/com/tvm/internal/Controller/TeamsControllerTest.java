package com.tvm.internal.Controller;

import com.tvm.internal.edit.controller.TeamsController;
import com.tvm.internal.edit.model.Teams;
import com.tvm.internal.edit.serviceImpl.TeamsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TeamsControllerTest {

    @InjectMocks
    private TeamsController teamsController;

    @Mock
    private TeamsServiceImpl teamService;

    private Teams team;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        team = new Teams();
        team.setTeamId(1);
        team.setTeamName("Development Team");
        team.setEmployees(new ArrayList<>()); // Assuming Employee is an entity class
        team.setGoals(new ArrayList<>()); // Assuming Goal is an entity class
    }

    @Test
    void testGetAllTeams() {
        List<Teams> teamsList = new ArrayList<>();
        teamsList.add(team);

        when(teamService.getAllTeams()).thenReturn(teamsList);

        List<Teams> teams = teamsController.getAllTeams();

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(1);
        assertThat(teams.get(0)).isEqualTo(team);
        verify(teamService, times(1)).getAllTeams();
    }

    @Test
    void testGetTeamById() {
        when(teamService.getTeamById(anyInt())).thenReturn(Optional.of(team));

        ResponseEntity<Teams> response = teamsController.getTeamById(1);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(team);
        verify(teamService, times(1)).getTeamById(1);
    }

    @Test
    void testGetTeamByIdNotFound() {
        when(teamService.getTeamById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Teams> response = teamsController.getTeamById(999);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        verify(teamService, times(1)).getTeamById(999);
    }

    @Test
    void testCreateTeam() {
        when(teamService.saveTeam(any(Teams.class))).thenReturn(team);

        Teams createdTeam = teamsController.createTeam(team);

        assertThat(createdTeam).isNotNull();
        assertThat(createdTeam.getTeamId()).isEqualTo(team.getTeamId());
        verify(teamService, times(1)).saveTeam(any(Teams.class));
    }

    @Test
    void testDeleteTeam() {
        doNothing().when(teamService).deleteTeam(anyInt());

        ResponseEntity<Void> response = teamsController.deleteTeam(1);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(teamService, times(1)).deleteTeam(1);
    }
}
