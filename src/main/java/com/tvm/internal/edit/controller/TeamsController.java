package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Teams;
import com.tvm.internal.edit.serviceImpl.TeamsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    @Autowired
    private TeamsServiceImpl teamService;

    @GetMapping
    public List<Teams> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Teams> getTeamById(@PathVariable int teamId) {
        return teamService.getTeamById(teamId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teams createTeam(@RequestBody Teams team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }
}