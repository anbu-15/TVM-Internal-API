package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Announcements;
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

    public List<Announcements> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Optional<Announcements> getAnnouncementById(Long id) {
        return announcementRepository.findById(id);
    }

    public Announcements createAnnouncement(Announcements announcement, MultipartFile file) throws IOException {
        if (file != null) {
            announcement.setAttachment(file.getBytes());
        }
        return announcementRepository.save(announcement);
    }

    public Announcements updateAnnouncement(Long id, Announcements announcement, MultipartFile file) throws IOException {
        announcement.setId(id);
        if (file != null) {
            announcement.setAttachment(file.getBytes());
        }
        return announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}