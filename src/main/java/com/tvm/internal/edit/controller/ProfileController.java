package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Profile;
import com.tvm.internal.edit.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.createProfile(profile));
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        try {
            Profile profile = profileService.getProfileById(id);
            return ResponseEntity.ok(profile);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        try {
            Profile updatedProfile = profileService.updateProfile(id, profile);
            return ResponseEntity.ok(updatedProfile);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        try {
            profileService.deleteProfile(id);
            return ResponseEntity.ok("Deleted Successfully!!!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
