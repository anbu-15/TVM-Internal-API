package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Announcement;
import com.tvm.internal.edit.serviceImpl.AnnouncementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AnnouncementControllerTest {

    @InjectMocks
    private AnnouncementController announcementController;

    @Mock
    private AnnouncementService announcementService;

    @Mock
    private MultipartFile file;  // Mocking MultipartFile

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        announcements.add(new Announcement());
        when(announcementService.getAllAnnouncements()).thenReturn(announcements);

        List<Announcement> response = announcementController.getAllAnnouncements();
        assertEquals(1, response.size());
        verify(announcementService, times(1)).getAllAnnouncements();
    }

    @Test
    void getAnnouncementById_found() {
        Announcement announcement = new Announcement();
        announcement.setId(1L);
        when(announcementService.getAnnouncementById(1L)).thenReturn(Optional.of(announcement));

        ResponseEntity<Announcement> response = announcementController.getAnnouncementById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(announcement, response.getBody());
    }

    @Test
    void getAnnouncementById_notFound() {
        when(announcementService.getAnnouncementById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Announcement> response = announcementController.getAnnouncementById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createAnnouncement_success() throws IOException {
        Announcement announcement = new Announcement();
        announcement.setId(1L);
        when(announcementService.createAnnouncement(any(Announcement.class), any(MultipartFile.class)))
                .thenReturn(announcement);

        ResponseEntity<Announcement> response = announcementController.createAnnouncement(
                "John Doe", "Test Title", "Test Message", "Test Category",
                "2024-12-31", "Test Location", file
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(announcement, response.getBody());
    }

    @Test
    void createAnnouncement_ioException() throws IOException {
        when(announcementService.createAnnouncement(any(Announcement.class), any(MultipartFile.class)))
                .thenThrow(new IOException("File error"));

        ResponseEntity<Announcement> response = announcementController.createAnnouncement(
                "John Doe", "Test Title", "Test Message", "Test Category",
                "2024-12-31", "Test Location", file
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateAnnouncement_success() throws IOException {
        Announcement announcement = new Announcement();
        announcement.setId(1L);
        when(announcementService.updateAnnouncement(anyLong(), any(Announcement.class), any(MultipartFile.class)))
                .thenReturn(announcement);

        ResponseEntity<Announcement> response = announcementController.updateAnnouncement(
                1L, "John Doe", "Updated Title", "Updated Message", "Updated Category",
                "2025-01-01", "Updated Location", file
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(announcement, response.getBody());
    }

    @Test
    void updateAnnouncement_ioException() throws IOException {
        when(announcementService.updateAnnouncement(anyLong(), any(Announcement.class), any(MultipartFile.class)))
                .thenThrow(new IOException("File error"));

        ResponseEntity<Announcement> response = announcementController.updateAnnouncement(
                1L, "John Doe", "Updated Title", "Updated Message", "Updated Category",
                "2025-01-01", "Updated Location", file
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deleteAnnouncement_success() {
        doNothing().when(announcementService).deleteAnnouncement(anyLong());

        ResponseEntity<Void> response = announcementController.deleteAnnouncement(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(announcementService, times(1)).deleteAnnouncement(1L);
    }
}
