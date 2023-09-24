package com.aikoni6.project.Repositories.general;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.general.ProfileAttributes;

public interface ProfileAttributesRepo extends JpaRepository<ProfileAttributes, java.lang.Long> {
    public ProfileAttributes findByProfileAndForProfile(Long Profile, Long forProfile);
}
