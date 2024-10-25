package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Announcement;
import com.tvm.internal.edit.repo.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Optional<Announcement> getAnnouncementById(Long id) {
        return announcementRepository.findById(id);
    }

    public Announcement updateAnnouncement(Long id, Announcement updatedAnnouncement) {
        if (announcementRepository.existsById(id)) {
            updatedAnnouncement.setId(id);
            return announcementRepository.save(updatedAnnouncement);
        } else {
            throw new IllegalArgumentException("Announcement not found");
        }
    }

    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}