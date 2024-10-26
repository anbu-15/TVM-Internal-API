package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Announcement;
import com.tvm.internal.edit.repo.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Optional<Announcement> getAnnouncementById(Long id) {
        return announcementRepository.findById(id);
    }

    public Announcement createAnnouncement(Announcement announcement, MultipartFile file) throws IOException {
        if (file != null) {
            announcement.setAttachment(file.getBytes()); // Store the file as bytes
        }
        return announcementRepository.save(announcement);
    }

    public Announcement updateAnnouncement(Long id, Announcement announcement, MultipartFile file) throws IOException {
        announcement.setId(id);
        if (file != null) {
            announcement.setAttachment(file.getBytes()); // Store the new file as bytes
        }
        return announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}