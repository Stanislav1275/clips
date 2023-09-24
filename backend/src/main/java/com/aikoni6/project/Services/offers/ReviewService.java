package com.aikoni6.project.Services.offers;

import com.aikoni6.project.Entities.offers.Review;
import com.aikoni6.project.Repositories.offers.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    public List<Review> getOnOrder(Long order) {
        return reviewRepo.findByOnOrder(order);
    }
    public Review getByID(Long ID){
        return reviewRepo.findById(ID).orElse(null);
    }
    public Review saveReview(Review value){
        if(value == null) return null;
        return reviewRepo.save(value);
    }
}
