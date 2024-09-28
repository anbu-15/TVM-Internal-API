package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Teams;

import java.util.List;
import java.util.Optional;

public interface TeamsService {

    public List<Teams> getAllTeams();
    public Optional<Teams> getTeamById(int teamId);

    public Teams saveTeam(Teams team);

    public void deleteTeam(int teamId);
}
