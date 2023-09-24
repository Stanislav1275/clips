package com.aikoni6.project.Repositories.offers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.offers.Review;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    public List<Review> findByOnOrder(Long order);
}
