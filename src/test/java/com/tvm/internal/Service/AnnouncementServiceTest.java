package com.tvm.internal.Service;

import com.tvm.internal.edit.model.Announcements;
import com.tvm.internal.edit.repo.AnnouncementRepository;
import com.tvm.internal.edit.serviceImpl.AnnouncementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnnouncementServiceTest {

    @Mock
    private AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementService announcementService;

    private Announcements announcement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        announcement = new Announcements();
        announcement.setId(1L);
        announcement.setName("Test Announcement");
        announcement.setTitle("Title");
        announcement.setMessage("This is a test announcement.");
        announcement.setCategory("General");
        LocalDate futureDate = LocalDate.now().plusDays(5);
        LocalDateTime futureDateTime = futureDate.atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(futureDateTime);
        announcement.setExpiry(timestamp);
        announcement.setLocation("Location 1");
    }

    @Test
    void getAllAnnouncements() {
        when(announcementRepository.findAll()).thenReturn(Arrays.asList(announcement));
        List<Announcements> announcements = announcementService.getAllAnnouncements();
        assertNotNull(announcements);
        assertEquals(1, announcements.size());
        assertEquals("Test Announcement", announcements.get(0).getName());
    }

    @Test
    void getAnnouncementById_existingId() {
        when(announcementRepository.findById(1L)).thenReturn(Optional.of(announcement));
        Optional<Announcements> foundAnnouncement = announcementService.getAnnouncementById(1L);
        assertTrue(foundAnnouncement.isPresent());
        assertEquals("Test Announcement", foundAnnouncement.get().getName());
    }

    @Test
    void getAnnouncementById_nonExistingId() {
        when(announcementRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Announcements> foundAnnouncement = announcementService.getAnnouncementById(1L);
        assertFalse(foundAnnouncement.isPresent());
    }

    @Test
    void createAnnouncement_withAttachment() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test File Content".getBytes());
        when(announcementRepository.save(any(Announcements.class))).thenReturn(announcement);
        Announcements savedAnnouncement = announcementService.createAnnouncement("announcement", file);
        assertNotNull(savedAnnouncement);
        assertEquals("Test Announcement", savedAnnouncement.getName());
        assertNotNull(savedAnnouncement.getAttachment());
    }

    @Test
    void createAnnouncement_withoutAttachment() throws IOException {
        when(announcementRepository.save(any(Announcements.class))).thenReturn(announcement);
        Announcements savedAnnouncement = announcementService.createAnnouncement("announcement", null);
        assertNotNull(savedAnnouncement);
        assertEquals("Test Announcement", savedAnnouncement.getName());
        assertNull(savedAnnouncement.getAttachment());
    }

    @Test
    void updateAnnouncement_withAttachment() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Updated File Content".getBytes());
        when(announcementRepository.save(any(Announcements.class))).thenReturn(announcement);
        Announcements updatedAnnouncement = announcementService.updateAnnouncement(1L, announcement, file);
        assertNotNull(updatedAnnouncement);
        assertEquals("Test Announcement", updatedAnnouncement.getName());
        assertNotNull(updatedAnnouncement.getAttachment());
    }

    @Test
    void updateAnnouncement_withoutAttachment() throws IOException {
        when(announcementRepository.save(any(Announcements.class))).thenReturn(announcement);
        Announcements updatedAnnouncement = announcementService.updateAnnouncement(1L, announcement, null);
        assertNotNull(updatedAnnouncement);
        assertEquals("Test Announcement", updatedAnnouncement.getName());
        assertNull(updatedAnnouncement.getAttachment());
    }

    @Test
    void deleteAnnouncement_existingId() {
        doNothing().when(announcementRepository).deleteById(1L);
        announcementService.deleteAnnouncement(1L);
        verify(announcementRepository).deleteById(1L);
    }
}
