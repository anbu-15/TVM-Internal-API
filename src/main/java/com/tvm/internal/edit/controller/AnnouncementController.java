package com.tvm.internal.edit.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.model.Announcements;
import com.tvm.internal.edit.serviceImpl.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<Announcements> createAnnouncement(
            @RequestPart("announcement") String announcementJson,
            @RequestPart(value = "attachment", required = false) MultipartFile attachment) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Announcements announcement = objectMapper.readValue(announcementJson, Announcements.class);

            if (attachment != null) {
                announcement.setAttachment(attachment.getBytes()); // Ensure your Announcement model can handle the photo data
            }

            Announcements createdAnnouncement = announcementService.createAnnouncement(announcement, attachment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnnouncement);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Announcements> updateAnnouncement(
            @PathVariable Long id,
            @RequestPart("announcement") String announcementJson,
            @RequestPart(value = "attachment", required = false) MultipartFile attachment) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Announcements updatedAnnouncement = objectMapper.readValue(announcementJson, Announcements.class);

            if (attachment != null) {
                updatedAnnouncement.setAttachment(attachment.getBytes());
            }

            Announcements announcement = announcementService.updateAnnouncement(id, updatedAnnouncement, attachment);
            return ResponseEntity.ok(announcement);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Announcements>> getAllAnnouncements() {
        List<Announcements> announcements = announcementService.getAllAnnouncements();

        if (announcements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcements> getAnnouncementById(@PathVariable Long id) {
        Optional<Announcements> announcement = announcementService.getAnnouncementById(id);

        if (announcement.isPresent()) {
            return ResponseEntity.ok(announcement.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}
