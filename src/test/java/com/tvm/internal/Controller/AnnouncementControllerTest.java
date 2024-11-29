package com.tvm.internal.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.internal.edit.controller.AnnouncementController;
import com.tvm.internal.edit.model.Announcements;
import com.tvm.internal.edit.serviceImpl.AnnouncementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<Announcements> announcements = new ArrayList<>();
        announcements.add(new Announcements());
        when(announcementService.getAllAnnouncements()).thenReturn(announcements);

        ResponseEntity<List<Announcements>> response = announcementController.getAllAnnouncements();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(announcementService, times(1)).getAllAnnouncements();
    }


    @Test
    void getAnnouncementById_found() {
        Announcements announcement = new Announcements();
        announcement.setId(1L);
        when(announcementService.getAnnouncementById(1L)).thenReturn(Optional.of(announcement));

        ResponseEntity<Announcements> response = announcementController.getAnnouncementById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(announcement, response.getBody());
    }

    @Test
    void getAnnouncementById_notFound() {
        when(announcementService.getAnnouncementById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Announcements> response = announcementController.getAnnouncementById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createAnnouncement_success() throws IOException {
        Announcements announcement = new Announcements();
        announcement.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        when(announcementService.createAnnouncement(anyString(), any(MultipartFile.class)))
                .thenReturn(announcement);

        ResponseEntity<Announcements> response = announcementController.createAnnouncement(
                Mockito.anyString(),file
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(announcement, response.getBody());
        verify(announcementService, times(1))
                .createAnnouncement(anyString(), any(MultipartFile.class));
    }

    @Test
    void createAnnouncement_internalServerError() throws IOException {
        Announcements announcement = new Announcements();
        ObjectMapper objectMapper = new ObjectMapper();
        String announcementJson = objectMapper.writeValueAsString(announcement);

        // Mock service to throw an exception
        when(announcementService.createAnnouncement(anyString(), any(MultipartFile.class)))
                .thenThrow(new IOException("Test Exception"));

        // Act: Call the controller method
        ResponseEntity<Announcements> response = announcementController.createAnnouncement(announcementJson, file);

        // Assert: Verify the response status for error case
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    void createAnnouncement_ioException() throws IOException {
        when(announcementService.createAnnouncement(anyString(), any(MultipartFile.class)))
                .thenThrow(new IOException("File error"));

        ResponseEntity<Announcements> response = announcementController.createAnnouncement(
                "John Doe",  file
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateAnnouncement_success() throws IOException {
        Long announcementId = 1L;
        Announcements announcement = new Announcements();
        announcement.setId(announcementId);  // Set the ID or any other relevant fields
        ObjectMapper objectMapper = new ObjectMapper();
        String announcementJson = objectMapper.writeValueAsString(announcement);
        when(announcementService.updateAnnouncement(eq(announcementId), any(Announcements.class), any(MultipartFile.class)))
                .thenReturn(announcement);

        // Mock the MultipartFile to simulate an uploaded file
        when(file.getBytes()).thenReturn("fileContent".getBytes());

        // Act: Call the controller method
        ResponseEntity<Announcements> response = announcementController.updateAnnouncement(announcementId, announcementJson, file);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(announcement, response.getBody());

        // Verify that the service method was called once with the expected parameters
        verify(announcementService, times(1))
                .updateAnnouncement(eq(announcementId), any(Announcements.class), any(MultipartFile.class));
    }

    @Test
    void updateAnnouncement_ioException() throws IOException {
        when(announcementService.updateAnnouncement(anyLong(), any(Announcements.class), any(MultipartFile.class)))
                .thenThrow(new IOException("File error"));

        ResponseEntity<Announcements> response = announcementController.updateAnnouncement(
                1L, "John Doe", file
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
