package com.aikoni6.project.Services.offers;

import com.aikoni6.project.Entities.offers.FreelanceOffer;
import com.aikoni6.project.Entities.offers.JobTier;
import com.aikoni6.project.Repositories.offers.JobTierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobTierService {
    @Autowired
    private JobTierRepo jobTierRepo;

    public List<JobTier> getTiersByOffer(FreelanceOffer offer){
        return jobTierRepo.findByOffer(offer.getId());
    }
    public JobTier getTierByID(Long id){
        return jobTierRepo.findById(id).orElse(null);
    }
    public List<JobTier> getTiersByID(Long id){
        List<JobTier> res = new ArrayList<>();
        JobTier start = getTierByID(id);
        while(start.getPrevious() != null){
            start = getTierByID(start.getId());
        }
        while (start.getNext() != null){
            res.add(start);
        }
        res.add(start);

        return res;
    }
    public JobTier saveTier(JobTier tier){
        tier = jobTierRepo.save(tier);
        if(tier.getPrevious() != null){
            var prev = jobTierRepo.findById(tier.getPrevious()).orElse(null);
            if(prev == null) return null;
            prev.setNext(tier.getId());
            jobTierRepo.save(prev);
        }
        return tier;
    }
}
