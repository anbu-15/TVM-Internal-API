package com.tvm.internal.Service;

import com.tvm.internal.edit.model.Teams;
import com.tvm.internal.edit.repo.TeamsRepo;
import com.tvm.internal.edit.serviceImpl.TeamsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamsServiceImplTest {

    @Mock
    private TeamsRepo teamsRepo;

    @InjectMocks
    private TeamsServiceImpl teamsService;

    private Teams team;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        team = new Teams();
        team.setTeamId(1);
        team.setTeamName("Development");
    }

    @Test
    void getAllTeams() {
        when(teamsRepo.findAll()).thenReturn(Arrays.asList(team));

        List<Teams> teams = teamsService.getAllTeams();
        assertEquals(1, teams.size());
        assertEquals("Development", teams.get(0).getTeamName());
        verify(teamsRepo, times(1)).findAll();
    }

    @Test
    void getTeamById_existingId() {
        when(teamsRepo.findById(1)).thenReturn(Optional.of(team));

        Optional<Teams> foundTeam = teamsService.getTeamById(1);
        assertTrue(foundTeam.isPresent());
        assertEquals("Development", foundTeam.get().getTeamName());
        verify(teamsRepo, times(1)).findById(1);
    }

    @Test
    void getTeamById_nonExistingId() {
        when(teamsRepo.findById(2)).thenReturn(Optional.empty());

        Optional<Teams> foundTeam = teamsService.getTeamById(2);
        assertFalse(foundTeam.isPresent());
        verify(teamsRepo, times(1)).findById(2);
    }

    @Test
    void saveTeam() {
        when(teamsRepo.save(team)).thenReturn(team);

        Teams savedTeam = teamsService.saveTeam(team);
        assertNotNull(savedTeam);
        assertEquals("Development", savedTeam.getTeamName());
        verify(teamsRepo, times(1)).save(team);
    }

    @Test
    void deleteTeam() {
        doNothing().when(teamsRepo).deleteById(1);

        teamsService.deleteTeam(1);
        verify(teamsRepo, times(1)).deleteById(1);
    }
}
