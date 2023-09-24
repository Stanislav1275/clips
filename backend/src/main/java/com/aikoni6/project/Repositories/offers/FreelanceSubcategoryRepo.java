package com.aikoni6.project.Repositories.offers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.offers.FreelanceSubcategory;

import java.util.List;

public interface FreelanceSubcategoryRepo extends JpaRepository<FreelanceSubcategory, Long> {
    public List<FreelanceSubcategory> findFreelanceSubcategoryByCategory(Long category);
    public List<FreelanceSubcategory> findFreelanceSubcategoryByName(String name);
}
