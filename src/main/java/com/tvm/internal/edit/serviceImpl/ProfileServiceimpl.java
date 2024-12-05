package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Profile;
import com.tvm.internal.edit.repo.ProfileRepository;
import com.tvm.internal.edit.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfileServiceimpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepo;

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepo.save(profile);
    }

    @Override
    public Profile getProfileById(Long id) {
        return profileRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Profile with ID " + id + " not found."));
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepo.findAll();
    }

    @Override
    public Profile updateProfile(Long id, Profile profile) {
        Profile existingProfile = profileRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Profile with ID " + id + " not found."));
        profile.setId(existingProfile.getId()); // Preserve the ID of the existing profile
        return profileRepo.save(profile);
    }

    @Override
    public void deleteProfile(Long id) {
        if (!profileRepo.existsById(id)) {
            throw new NoSuchElementException("Profile with ID " + id + " not found.");
        }
        profileRepo.deleteById(id);
    }
}
