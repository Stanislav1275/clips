package com.aikoni6.project.Controllers;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Entities.offers.FreelanceCategory;
import com.aikoni6.project.Entities.offers.FreelanceOffer;
import com.aikoni6.project.Entities.offers.FreelanceSubcategory;
import com.aikoni6.project.Entities.offers.JobTier;
import com.aikoni6.project.Services.general.ProfileService;
import com.aikoni6.project.Services.offers.FreelanceService;

import com.aikoni6.project.Services.offers.JobTierService;
import com.aikoni6.project.Services.system.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("freelance")
public class FreelanceController {
    @Autowired
    private FreelanceService freelanceService;
    @Autowired
    private JobTierService jobTierService;
    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    @CrossOrigin
    @GetMapping("offer")
    public ResponseEntity<String> getOffers(
            @RequestParam(value = "TOKEN", required = false) String token,
            @RequestParam("SUBCATEGORY") FreelanceSubcategory subcategory){

        if(token != null)
            return ResponseEntity.ok().body(
                    new ObjectMapper().writeValueAsString(
                            freelanceService.getOffersBySubcategory(subcategory, token)));

        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(freelanceService.getOffersBySubcategory(subcategory)));

    }
    @SneakyThrows
    @CrossOrigin
    @GetMapping("offer/{offer_id}")
    public ResponseEntity<String> getOffer(@RequestParam(value = "TOKEN" ,required = false) String token, @PathVariable("offer_id") Long offerID){
        FreelanceOffer res;
        if(token != null && !token.isEmpty())
            res = freelanceService.getOfferByID(offerID, token);
        else
            res = freelanceService.getOfferByID(offerID);

        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(res));
    }

    @CrossOrigin
    @DeleteMapping("offer/{offer_id}")
    public ResponseEntity<String> deleteOffer(@RequestParam("TOKEN") String token, @PathVariable("offer_id") Long offerID){
        FreelanceOffer offer = freelanceService.getOfferByID(offerID);
        Profile user = tokenService.getUserByToken(token);
        if(user != null && offer != null && offer.getCreator() == user.getId()) {
            freelanceService.deleteOffer(offer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @SneakyThrows
    @CrossOrigin
    @PostMapping("offer")
    public ResponseEntity<String> makeOffer(@RequestParam("OFFER") FreelanceOffer offer){
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(freelanceService.saveOffer(offer)));
    }
    @SneakyThrows
    @CrossOrigin
    @GetMapping("category")
    public ResponseEntity<String> getCategory(){
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(freelanceService.getCategories()));
    }
    @SneakyThrows
    @CrossOrigin
    @GetMapping("subcategory")
    public ResponseEntity<String> getSubcategory(){
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(freelanceService.getSubcategories()));
    }
    @SneakyThrows
    @CrossOrigin
    @GetMapping("job_tier/{ID}")
    public ResponseEntity<String> getJobTiersByID(@PathVariable("ID") Long id){
        var res = jobTierService.getTiersByID(id);
        if(res != null) return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(res));
        return ResponseEntity.badRequest().build();
    }
    @SneakyThrows
    @CrossOrigin
    @GetMapping("job_tier")
    public ResponseEntity<String> getJobTiersByOffer(@RequestParam("ID") Long id){
        var res = jobTierService.getTiersByOffer(freelanceService.getOfferByID(id));
        if(res != null) return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(res));
        return ResponseEntity.badRequest().build();
    }
    @SneakyThrows
    @CrossOrigin
    @PostMapping("job_tier")
    public ResponseEntity<String> saveTier(@RequestParam("TIER") JobTier tier){
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(jobTierService.saveTier(tier)));
    }
}
