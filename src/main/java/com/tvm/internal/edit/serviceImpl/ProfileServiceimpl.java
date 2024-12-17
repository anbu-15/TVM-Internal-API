package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Profile;
import com.tvm.internal.edit.repo.ProfileRepository;
import com.tvm.internal.edit.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProfileServiceimpl implements ProfileService {

    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceimpl.class);

    @Autowired
    private ProfileRepository profileRepo;

    @Override
    public Profile createProfile(Profile profile) {
        Profile createdProfile = profileRepo.save(profile);
        logger.info("Profile created: {}", createdProfile);
        return createdProfile;
    }

    @Override
    public Profile getProfileById(Long id) {
        Profile profile = profileRepo.findById(id).orElse(null);
        if (profile == null) {
            logger.warn("Profile not found for ID: {}", id);
        }
        return profile;
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = profileRepo.findAll();
        logger.info("Total profiles retrieved: {}", profiles.size());
        return profiles;
    }

    @Override
    public Profile updateProfile(Long id, Profile profile) {
        Profile existingProfile = profileRepo.findById(id).orElse(null);
        if (existingProfile != null) {
            profile.setId(existingProfile.getId());
            Profile updatedProfile = profileRepo.save(profile);
            logger.info("Profile updated: {}", updatedProfile);
            return updatedProfile;
        } else {
            logger.warn("Profile not found for update, ID: {}", id);
            return null;
        }
    }

    @Override
    public boolean deleteProfile(Long id) {
        if (profileRepo.existsById(id)) {
            profileRepo.deleteById(id);
            logger.info("Profile deleted for ID: {}", id);
            return true;
        } else {
            logger.warn("Profile not found for deletion, ID: {}", id);
            return false;
        }
    }
}
