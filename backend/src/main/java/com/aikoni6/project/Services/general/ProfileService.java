package com.aikoni6.project.Services.general;

import com.aikoni6.project.Entities.system.FileEntity;
import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Entities.general.ProfileAttributes;
import com.aikoni6.project.Repositories.general.ProfileRepo;
import com.aikoni6.project.Repositories.general.ProfileAttributesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private FileEntityService fileEntityService;
    @Autowired
    public ProfileAttributesRepo profileAttributesRepo;

    public Profile SignUp(String email, String password) {
        List<Profile> users = profileRepo.findByEmail(email);
        Boolean isExist = false;
        for (Profile user:
             users) {
            if (user.getIsDeleted() == true) {
                isExist = true;
                break;
            }
        }
        if(!isExist){
            Profile profile = new Profile();

            profile.setEmail(email);
            profile.setPassword(password);

            FileEntity profilePic = fileEntityService.makeRandomAvatar();
            profile.setProfilePic(profilePic.getId());

            return profileRepo.save(profile);
        }
        return null;
    }
    public Profile SignIn(String email, String password){
        List<Profile> profiles = profileRepo.findByEmailAndPassword(email, password);
        if(profiles.size() == 0)return null; // account not found
        Profile latestProfile = null;
        for(Profile profile : profiles) {
            if(profile.getIsDeleted() == false) {
                latestProfile = profile;
                break;
            }
            if(profile.getIsDeleted() == true) latestProfile = profile;
        }
        return latestProfile; //if account gets deleted we'll still return user and will parse state on client
    }
    public void delete(String email, String password){
        List<Profile> profiles = profileRepo.findByEmailAndPassword(email, password);
        profiles.forEach(profile -> {
            if(profile.getIsDeleted() == false) 
                profile.setIsDeleted(true);
            else profile.setIsDeleted(null);
            
            profileRepo.save(profile);
        });
    }
    public Profile restore(String email, String password) {
        List<Profile> profiles = profileRepo.findByEmailAndPassword(email, password);
        if(profiles.size() == 0) return null;
        for(Profile profile : profiles) if(profile.getIsDeleted() == true) {
            profile.setIsDeleted(false);
            return profileRepo.save(profile);
        }
        return null;
    }
    public Profile getProfileByID(Long id){
        Optional<Profile> profile =  profileRepo.findById(id);
        if(profile.isPresent()) return profile.get();
        return null;
        //throw new RuntimeException("Error: illegal profile request on profile " + id + "! Check for data in backups");
    }
    public Profile updateProfile(Profile profile){
        if(profile == null) return null;
        return profileRepo.save(profile);
    }
    public Boolean isBlocked(Profile user1, Profile user2){
        ProfileAttributes user2on1 = profileAttributesRepo.findByProfileAndForProfile(user2.getId(), user1.getId());
        return user2on1 != null && user2on1.getIsBlocked();
    }
    public Boolean canContact(Profile user1, Profile user2){
        if(user1 == null || user2 == null) return false;
        if(isBlocked(user1, user2) || isDeleted(user2) || isDeleted(user1)) return false;
        if(onFollow(user1, user2)) return true;
        if(!isPrivate(user2)) return true;
        return false;
    }
    public Boolean isDeleted(Profile user){
        return user.getIsDeleted();
    }
    public Boolean onFollow(Profile user1, Profile user2){
        ProfileAttributes onUser2 = profileAttributesRepo.findByProfileAndForProfile(user1.getId(), user2.getId());
        ProfileAttributes onUser1 = profileAttributesRepo.findByProfileAndForProfile(user2.getId(), user1.getId());
        return onUser1.getIsFollowing() || onUser2.getIsFollowing();
    }
    public Boolean isPrivate(Profile user1){
        return user1.getIsPrivate();
    }
}
