package com.tvm.internal.Service;

import com.tvm.internal.edit.model.TeamList;
import com.tvm.internal.edit.repo.TeamListRepository;
import com.tvm.internal.edit.service.TeamListService;
import com.tvm.internal.edit.serviceImpl.TeamListServiceImpl;
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

class TeamListServiceImplTest {

    @Mock
    private TeamListRepository teamListRepository;

    @InjectMocks
    private TeamListServiceImpl teamListService;

    private TeamList teamMember;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        teamMember = new TeamList();
        teamMember.setTeamMemberId(1L);
        teamMember.setFirstName("John");
        teamMember.setLastName("Doe");
        teamMember.setEmailAddress("john.doe@example.com");
        // Add other fields as necessary
    }

    @Test
    void getAllTeamMembers() {
        when(teamListRepository.findAll()).thenReturn(Arrays.asList(teamMember));

        List<TeamList> members = teamListService.getAllTeamMembers();

        assertNotNull(members);
        assertEquals(1, members.size());
        assertEquals("John", members.get(0).getFirstName());
    }

    @Test
    void getTeamMemberById_existingId() {
        when(teamListRepository.findById(1L)).thenReturn(Optional.of(teamMember));

        Optional<TeamList> foundMember = teamListService.getTeamMemberById(1L);

        assertTrue(foundMember.isPresent());
        assertEquals("John", foundMember.get().getFirstName());
    }

    @Test
    void getTeamMemberById_nonExistingId() {
        when(teamListRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<TeamList> foundMember = teamListService.getTeamMemberById(1L);

        assertFalse(foundMember.isPresent());
    }

    @Test
    void createTeamMember() {
        when(teamListRepository.save(any(TeamList.class))).thenReturn(teamMember);

        TeamList savedMember = teamListService.createTeamMember(teamMember);

        assertNotNull(savedMember);
        assertEquals("John", savedMember.getFirstName());
    }

    @Test
    void updateTeamMember_existingId() {
        when(teamListRepository.findById(1L)).thenReturn(Optional.of(teamMember));
        when(teamListRepository.save(any(TeamList.class))).thenReturn(teamMember);

        TeamList updatedMember = teamListService.updateTeamMember(1L, teamMember);

        assertNotNull(updatedMember);
        assertEquals("John", updatedMember.getFirstName());
        verify(teamListRepository).save(teamMember);
    }

    @Test
    void updateTeamMember_nonExistingId() {
        when(teamListRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            teamListService.updateTeamMember(1L, teamMember);
        });

        assertEquals("Team member not found", exception.getMessage());
    }

    @Test
    void deleteTeamMember_existingId() {
        doNothing().when(teamListRepository).deleteById(1L);

        teamListService.deleteTeamMember(1L);

        verify(teamListRepository).deleteById(1L);
    }


}
