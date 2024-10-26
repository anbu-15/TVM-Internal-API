package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Goal;
import com.tvm.internal.edit.model.Teams;
import com.tvm.internal.edit.service.GoalService;
import com.tvm.internal.edit.serviceImpl.GoalServiceImpl;
import com.tvm.internal.edit.serviceImpl.TeamsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TeamsController {

    @Autowired
    private TeamsServiceImpl teamService;

    @GetMapping("/teams")
    public List<Teams> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity<Teams> getTeamById(@PathVariable int teamId) {
        return teamService.getTeamById(teamId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/teams")
    public Teams createTeam(@RequestBody Teams team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

}