package com.tvm.internal.edit.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.model.TeamList;
import com.tvm.internal.edit.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/teamlist")
public class TeamListController {

    @Autowired
    private TeamListService teamListService;

    @GetMapping
    public List<TeamList> getAllTeamMembers() {
        return teamListService.getAllTeamMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamList> getTeamMemberById(@PathVariable Long id) {
        return teamListService.getTeamMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<TeamList> createTeamMember(
            @RequestPart("teamMember") String teamMemberJson, // Accept JSON as String
            @RequestPart("photo") MultipartFile photo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TeamList teamMember = objectMapper.readValue(teamMemberJson, TeamList.class);

            teamMember.setPhoto(photo.getBytes());

            TeamList createdTeamMember = teamListService.createTeamMember(teamMember);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeamMember);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamList> updateTeamMember(
            @PathVariable Long id,
            @RequestParam("teamMember") TeamList teamMember,
            @RequestParam("photo") MultipartFile photo) {
        try {
            teamMember.setPhoto(photo.getBytes());
            TeamList updatedTeamMember = teamListService.updateTeamMember(id, teamMember);
            return ResponseEntity.ok(updatedTeamMember);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable Long id) {
        teamListService.deleteTeamMember(id);
        return ResponseEntity.noContent().build();
    }
}
