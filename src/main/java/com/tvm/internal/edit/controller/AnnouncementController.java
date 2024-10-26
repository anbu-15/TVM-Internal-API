package com.tvm.internal.edit.controller;


import com.tvm.internal.edit.model.Announcement;
import com.tvm.internal.edit.serviceImpl.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
        Optional<Announcement> announcement = announcementService.getAnnouncementById(id);
        return announcement.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Announcement> createAnnouncement(
            @RequestParam("name") String name,
            @RequestParam("title") String title,
            @RequestParam("message") String message,
            @RequestParam("category") String category,
            @RequestParam("expiry") String expiry,
            @RequestParam("location") String location,
            @RequestParam("file") MultipartFile file) {
        Announcement announcement = new Announcement();
        announcement.setName(name);
        announcement.setTitle(title);
        announcement.setMessage(message);
        announcement.setCategory(category);
        announcement.setExpiry(LocalDate.parse(expiry));
        announcement.setLocation(location);

        try {
            Announcement createdAnnouncement = announcementService.createAnnouncement(announcement, file);
            return new ResponseEntity<>(createdAnnouncement, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("title") String title,
            @RequestParam("message") String message,
            @RequestParam("category") String category,
            @RequestParam("expiry") String expiry,
            @RequestParam("location") String location,
            @RequestParam("file") MultipartFile file) {
        Announcement announcement = new Announcement();
        announcement.setName(name);
        announcement.setTitle(title);
        announcement.setMessage(message);
        announcement.setCategory(category);
        announcement.setExpiry(LocalDate.parse(expiry));
        announcement.setLocation(location);

        try {
            Announcement updatedAnnouncement = announcementService.updateAnnouncement(id, announcement, file);
            return ResponseEntity.ok(updatedAnnouncement);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}