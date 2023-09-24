package com.aikoni6.project.Repositories.offers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.offers.JobTier;

import java.util.List;

public interface JobTierRepo extends JpaRepository<JobTier, Long> {
    public List<JobTier> findByOffer(Long offer);
}
