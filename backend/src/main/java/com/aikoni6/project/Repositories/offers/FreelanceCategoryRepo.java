package com.aikoni6.project.Repositories.offers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.offers.FreelanceCategory;

import java.util.List;

public interface FreelanceCategoryRepo extends JpaRepository<FreelanceCategory, Long> {
    public List<FreelanceCategory> findFreelanceCategoryByName(String name);
}
