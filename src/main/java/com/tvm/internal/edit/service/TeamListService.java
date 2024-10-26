package com.tvm.internal.edit.service;


import com.tvm.internal.edit.model.TeamList;

import java.util.List;
import java.util.Optional;

public interface TeamListService {
    List<TeamList> getAllTeamMembers();
    Optional<TeamList> getTeamMemberById(Long id);
    TeamList createTeamMember(TeamList teamMember);
    TeamList updateTeamMember(Long id, TeamList updatedTeamMember);
    void deleteTeamMember(Long id);
}

