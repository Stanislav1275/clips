package com.aikoni6.project.Controllers;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Services.general.ProfileService;
import com.aikoni6.project.Services.system.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController("user")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    @CrossOrigin
    @GetMapping("profile/{ID}")
    public ResponseEntity<String> getProfile(@PathVariable("ID") Long ID, @RequestParam(value = "TOKEN", required = false) String token){
        Profile res = profileService.getProfileByID(ID);
        if(res.getIsPrivate() && token == null) return ResponseEntity.badRequest().build();
        if(!res.getIsPrivate() || profileService.canContact(tokenService.getUserByToken(token), res)) return ResponseEntity.ok(new ObjectMapper().writeValueAsString(res));
        return ResponseEntity.badRequest().body("Error: could not find user");
    }
    @SneakyThrows
    @CrossOrigin
    @GetMapping("profile/self")
    public ResponseEntity<String> getSelf(@RequestParam("TOKEN") String token){
        Profile user = tokenService.getUserByToken(token);
        if(user != null) return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(user));
        return ResponseEntity.badRequest().build();
    }
    @SneakyThrows
    @CrossOrigin
    @PostMapping("profile")
    public ResponseEntity<String> changeSelf(@RequestParam("TOKEN") String token, @RequestParam("PROFILE") Profile profile){
        Profile user = tokenService.getUserByToken(token);
        if(Objects.equals(user.getId(), profile.getId()))
            return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(profileService.updateProfile(profile)));

        return ResponseEntity.badRequest().build();
    }
}
