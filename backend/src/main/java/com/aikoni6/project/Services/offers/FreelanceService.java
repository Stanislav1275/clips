package com.aikoni6.project.Services.offers;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Entities.offers.FreelanceCategory;
import com.aikoni6.project.Entities.offers.FreelanceOffer;
import com.aikoni6.project.Entities.offers.FreelanceSubcategory;
import com.aikoni6.project.Repositories.offers.FreelanceCategoryRepo;
import com.aikoni6.project.Repositories.offers.FreelanceOfferRepo;
import com.aikoni6.project.Repositories.offers.FreelanceSubcategoryRepo;

import com.aikoni6.project.Services.general.ProfileService;
import com.aikoni6.project.Services.system.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FreelanceService {
    @Autowired
    private FreelanceOfferRepo freelanceOfferRepo;
    @Autowired
    private FreelanceCategoryRepo freelanceCategoryRepo;
    @Autowired
    private FreelanceSubcategoryRepo freelanceSubcategoryRepo;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private TokenService tokenService;
    public List<FreelanceOffer> getOffersBySubcategory(FreelanceSubcategory freelanceSubcategory, String token) {
        var offers = freelanceOfferRepo.findBySubcategory(freelanceSubcategory.getId());
        List<FreelanceOffer> res = new ArrayList<>();
        for (FreelanceOffer offer:
             offers) {
            if(!offer.getIsPrivate() ||
                    profileService.canContact(tokenService.getUserByToken(token), profileService.getProfileByID(offer.getCreator())))
                        res.add(offer);
        }
        return res;
    }
    public List<FreelanceOffer> getOffersBySubcategory(FreelanceSubcategory freelanceSubcategory) {
        var offers = freelanceOfferRepo.findBySubcategory(freelanceSubcategory.getId());
        Profile user;
        List<FreelanceOffer> res = new ArrayList<>();
        for (FreelanceOffer offer:
             offers){
            user = profileService.getProfileByID(offer.getCreator());
            if(!offer.getIsPrivate() && !user.getIsPrivate() && !user.getIsDeleted()) res.add(offer);
        }
        return res;
    }
    public List<FreelanceOffer> getAllOffers(String token){
        List<FreelanceCategory> categories = freelanceCategoryRepo.findAll();
        List<FreelanceOffer> res = new ArrayList<>();
        for (FreelanceCategory category:
             categories) {
            res.addAll(getOffersByCategory(category,token));
        }
        return res;
    }
   public List<FreelanceOffer> getOffersByCategory(FreelanceCategory category, String token){
        List<FreelanceSubcategory> subcategories = freelanceSubcategoryRepo.findFreelanceSubcategoryByCategory(category.getId());
        List<FreelanceOffer> res = new ArrayList<>();
        for (FreelanceSubcategory subcategory:
             subcategories) {
            res.addAll(getOffersBySubcategory(subcategory,token));
        }
        return res;
    }
    public FreelanceOffer getOfferByID(Long ID, String token){
        Optional<FreelanceOffer> offer = freelanceOfferRepo.findById(ID);
        return offer.orElse(null);
    }
    public FreelanceOffer getOfferByID(Long ID){
        FreelanceOffer offer = freelanceOfferRepo.findById(ID).orElse(null);
        if(offer != null && !offer.getIsPrivate()) return offer;
        return null;
    }
    public List<FreelanceOffer> getOffersByCreator(Profile creator){
        return freelanceOfferRepo.findByCreator(creator.getId());
    }
    public void deleteOffer(FreelanceOffer offer){
        freelanceOfferRepo.delete(offer);
    }
    public List<FreelanceCategory> getCategories(){
        return freelanceCategoryRepo.findAll();
    }
    public List<FreelanceSubcategory> getSubcategories(){
        return freelanceSubcategoryRepo.findAll();
    }
    public FreelanceOffer saveOffer(FreelanceOffer offer){
        return freelanceOfferRepo.save(offer);
    }
}
