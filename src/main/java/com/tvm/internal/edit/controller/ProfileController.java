package com.tvm.internal.edit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.model.Profile;
import com.tvm.internal.edit.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestParam("profile") String profileJson, @RequestParam("EmployeePhoto") MultipartFile file) throws IOException {
        Profile profile = new ObjectMapper().readValue(profileJson, Profile.class);
        if (file != null && !file.isEmpty()) {
            logger.info("Processing uploaded file: {}", file.getOriginalFilename());
            profile.setEmployeePhoto(Arrays.toString(file.getBytes()));
        } else {
            logger.warn("No image file uploaded for profile.");
        }
        Profile createdProfile = profileService.createProfile(profile);
        logger.info("Profile created: {}", createdProfile);
        return ResponseEntity.ok(createdProfile);
    }

    @PostMapping("/clob")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        try {
            if (profile.getEmployeePhoto() != null && !profile.getEmployeePhoto().trim().isEmpty()) {
                byte[] decodedImage = java.util.Base64.getDecoder().decode(profile.getEmployeePhoto());
                profile.setEmployeePhoto(Arrays.toString(decodedImage));
            } else {
                logger.warn("No image data provided for profile.");
            }
            Profile createdProfile = profileService.createProfile(profile);
            logger.info("Profile created: {}", createdProfile);
            return ResponseEntity.ok(createdProfile);
        } catch (Exception e) {
            logger.error("Error while creating profile: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();

        profiles.forEach(profile -> {
            if (profile.getEmployeePhoto() != null) {
                String encodedImage = Base64.getEncoder().encodeToString(profile.getEmployeePhoto().getBytes());
                profile.setEmployeePhoto(encodedImage);
            }
        });

        logger.info("Retrieved all profiles, count: {}", profiles.size());
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        if (profile != null) {
            if (profile.getEmployeePhoto() != null) {
                String encodedImage = Base64.getEncoder().encodeToString(profile.getEmployeePhoto().getBytes());
                profile.setEmployeePhoto(encodedImage); // Set the Base64 string for response
            }
            logger.info("Profile retrieved for ID {}: {}", id, profile);
            return ResponseEntity.ok(profile);
        } else {
            logger.warn("Profile not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestParam("profile") String profileJson, @RequestParam("EmployeePhoto") MultipartFile file) throws IOException {
        Profile profile = new ObjectMapper().readValue(profileJson, Profile.class);
        if (file != null && !file.isEmpty()) {
            profile.setEmployeePhoto(Arrays.toString(file.getBytes()));
        } else {
            logger.warn("No image file uploaded for profile update.");
        }
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