package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Teams;
import com.tvm.internal.edit.repo.TeamsRepo;
import com.tvm.internal.edit.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    TeamsRepo repo;


    public List<Teams> getAllTeams() {
        return repo.findAll();
    }

    public Optional<Teams> getTeamById(int teamId) {
        return repo.findById(teamId);
    }

    public Teams saveTeam(Teams team) {
        return repo.save(team);
    }

    public void deleteTeam(int teamId) {
        repo.deleteById(teamId);
    }
}
