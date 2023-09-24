package com.aikoni6.project.Repositories.offers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.offers.FreelanceOffer;

import java.util.List;

public interface FreelanceOfferRepo extends JpaRepository<FreelanceOffer, Long> {
    public List<FreelanceOffer> findBySubcategory(Long subcategory);
    public List<FreelanceOffer> findByCreator(Long creator);
}
