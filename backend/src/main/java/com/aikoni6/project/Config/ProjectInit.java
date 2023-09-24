package com.aikoni6.project.Config;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Entities.offers.FreelanceCategory;
import com.aikoni6.project.Entities.offers.FreelanceOffer;
import com.aikoni6.project.Entities.offers.FreelanceSubcategory;
import com.aikoni6.project.Entities.offers.JobTier;
import com.aikoni6.project.Repositories.general.ProfileRepo;
import com.aikoni6.project.Repositories.offers.FreelanceCategoryRepo;
import com.aikoni6.project.Repositories.offers.FreelanceOfferRepo;
import com.aikoni6.project.Repositories.offers.FreelanceSubcategoryRepo;
import com.aikoni6.project.Repositories.offers.JobTierRepo;
import com.aikoni6.project.Services.general.ProfileService;
import com.aikoni6.project.Services.offers.JobTierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

@Service
public class ProjectInit {
    private final Environment env;
    private final FreelanceCategoryRepo freelanceCategoryRepo;
    private final FreelanceSubcategoryRepo freelanceSubcategoryRepo;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private FreelanceOfferRepo freelanceOfferRepo;
    @Autowired
    private JobTierService jobTierService;
    @Autowired
    private JobTierRepo jobTierRepo;

    @Autowired
    public ProjectInit(Environment env,
                       FreelanceCategoryRepo freelanceCategoryRepo,
                       FreelanceSubcategoryRepo freelanceSubcategoryRepo){
        this.env = env;
        this.freelanceCategoryRepo = freelanceCategoryRepo;
        this.freelanceSubcategoryRepo = freelanceSubcategoryRepo;
    }

    public void init(){
        var isFirstRun = Boolean.parseBoolean(env.getProperty("app.management.onFirstRun", "true"));
        if(isFirstRun) onFirstRun();
    }
    private void onFirstRun(){
        FreelanceCategory programming = createCategory("Программирование");
        FreelanceSubcategory web = createSubcategory(programming, "Веб-разработка");
        FreelanceSubcategory gameDev = createSubcategory(programming, "Разработка игр");

        FreelanceCategory design = createCategory("Дизайн");
        FreelanceSubcategory ui = createSubcategory(design, "Разработка UI/UX интерфейсов");
        FreelanceSubcategory brand = createSubcategory(design, "Дизайн бренда");

        Profile user = createGenericUser();
        Profile privateUser = createGenericUser();
        FreelanceOffer offer = createGenericOffer(user, ui);
        FreelanceOffer offer2 = createGenericOffer(user, brand);
        FreelanceOffer privateUserOffer = createGenericOffer(privateUser, brand);
        FreelanceOffer privateOffer2 = createPrivateOffer(privateUser, ui);
        JobTier privTiers = createTiers(privateUserOffer);
        JobTier priv = createTiers(privateOffer2);

        JobTier tiers = createTiers(offer);
        JobTier tiers2 = createTiers(offer2);
    }
    public FreelanceCategory createCategory(String name){
        FreelanceCategory res = new FreelanceCategory();
        res.setName(name);
        return freelanceCategoryRepo.save(res);
    }
    public FreelanceSubcategory createSubcategory(FreelanceCategory category, String name){
        FreelanceSubcategory res = new FreelanceSubcategory();
        res.setCategory(category.getId());
        res.setName(name);
        return freelanceSubcategoryRepo.save(res);
    }
    public Profile createGenericUser(){
        Profile user = profileService.SignUp("test@example.com", "12345678");
        user.setName("Test");
        user.setSurname("Subject");
        user.setMoDo("set up for testing purposes only, not indended for real usage, self-description of public user");
        return profileRepo.save(user);
    }
    public Profile createPrivateUser(){
        Profile user = profileService.SignUp("test@test.test", "00000000");
        user.setName("Private");
        user.setSurname("User");
        user.setMoDo("This is self-description of private user");
        user.setIsPrivate(true);
        return profileRepo.save(user);
    }
    public FreelanceOffer createGenericOffer(Profile user, FreelanceSubcategory subcategory){
        FreelanceOffer offer = new FreelanceOffer();
        offer.setCreator(user.getId());
        offer.setTitle("Объявление на тему "+ subcategory.getName());
        offer.setSubcategory(subcategory.getId());
        offer.setText("this offer is for "+ subcategory.getName() + ". If you have any questions to ask, please, feel free to contact me anytime");
        return freelanceOfferRepo.save(offer);
    }
    public FreelanceOffer createPrivateOffer(Profile user, FreelanceSubcategory subcategory){
        FreelanceOffer offer = createGenericOffer(user, subcategory);
        offer.setIsPrivate(true);
        return freelanceOfferRepo.save(offer);
    }
    public JobTier createTiers(FreelanceOffer offer){
        JobTier tier = new JobTier();
        tier.setOffer(offer.getId());
        tier.setName("Sample text "+ offer.getTitle());
        tier.setDescription("Lorem ipsum");
        tier.setCost(500L);
        tier = jobTierService.saveTier(tier);
        JobTier tier2 = new JobTier();
        tier2.setPrevious(tier.getId());
        tier2.setName("test "+ offer.getTitle());
        tier2.setDescription("mod check");
        tier2.setOffer(offer.getId());
        tier2.setCost(1000L);
        return jobTierRepo.save(tier2);
    }
}
