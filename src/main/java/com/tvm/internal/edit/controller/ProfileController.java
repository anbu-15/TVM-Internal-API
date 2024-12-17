package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Profile;
import com.tvm.internal.edit.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile createdProfile = profileService.createProfile(profile);
        logger.info("Profile created: {}", createdProfile);
        return ResponseEntity.ok(createdProfile);
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        logger.info("Retrieved all profiles, count: {}", profiles.size());
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        if (profile != null) {
            logger.info("Profile retrieved for ID {}: {}", id, profile);
            return ResponseEntity.ok(profile);
        } else {
            logger.warn("Profile not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Profile updatedProfile = profileService.updateProfile(id, profile);
        if (updatedProfile != null) {
            logger.info("Profile updated for ID {}: {}", id, updatedProfile);
            return ResponseEntity.ok(updatedProfile);
        } else {
            logger.warn("Profile not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        boolean isDeleted = profileService.deleteProfile(id);
        if (isDeleted) {
            logger.info("Profile deleted for ID: {}", id);
            return ResponseEntity.ok("Deleted Successfully!!!");
        } else {
            logger.warn("Profile not found for deletion, ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
