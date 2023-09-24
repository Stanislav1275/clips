package com.aikoni6.project.Repositories.general;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.general.Profile;

import java.util.List;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    List<Profile> findByEmail(String phone);
    List<Profile> findByEmailAndPassword(String phone, String password);
}
