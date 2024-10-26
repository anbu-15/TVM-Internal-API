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
import java.util.Optional;

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
    public Optional<TeamList> getTeamMemberById(@PathVariable Long id) {
        return teamListService.getTeamMemberById(id);

    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<TeamList> createTeamMember(
            @RequestPart("teamMember") String teamMemberJson,
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
            @RequestPart("teamMember") String teamMemberJson,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TeamList updatedTeamMember = objectMapper.readValue(teamMemberJson, TeamList.class);

            // Check if the photo was provided and set it
            if (photo != null && !photo.isEmpty()) {
                updatedTeamMember.setPhoto(photo.getBytes());
            }

            TeamList teamMember = teamListService.updateTeamMember(id, updatedTeamMember);

            if (teamMember != null) {
                return ResponseEntity.ok(teamMember);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Not found
            }
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
