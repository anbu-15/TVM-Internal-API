package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Profile;

import java.util.List;

public interface ProfileService {
    Profile createProfile(Profile profile);

    Profile updateProfile(Long id, Profile profile);

    Profile getProfileById(Long id);

    List<Profile> getAllProfiles();

    boolean deleteProfile(Long id);
}