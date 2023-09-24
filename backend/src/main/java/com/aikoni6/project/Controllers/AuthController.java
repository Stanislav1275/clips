package com.aikoni6.project.Controllers;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Services.system.TokenService;
import com.aikoni6.project.Services.general.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("auth")
@RequestMapping("auth")
public class AuthController {
    private ProfileService profileService;
    private TokenService tokenService;
    
    @Autowired
    public AuthController(ProfileService profileService, TokenService tokenService) {
        this.profileService = profileService;
        this.tokenService = tokenService;
    }

    @SneakyThrows
    @CrossOrigin
    @PostMapping(path = "sign_up")
    public ResponseEntity<String> SignUp(@RequestParam("EMAIL") String email, @RequestParam("PASSWORD") String password){
        Profile profile =  profileService.SignUp(email, password);

        if(profile != null) {
            JSONObject res = new JSONObject();
            try{
                res.put("PROFILE", new ObjectMapper().writeValueAsString(profile));
                var token = tokenService.tokenUtils.createToken(profile);
                res.put("TOKEN", new ObjectMapper().writeValueAsString(token));
            } catch(Exception e){
                System.out.println(new Exception ("Error: couldn't create JSON for ", e));
                throw new Exception(e);
            }
            return ResponseEntity.ok().body(res.toString());
        }
        return ResponseEntity.badRequest().body("Error: valid profile found");
    }

    @SneakyThrows
    @CrossOrigin
    @PostMapping("sign_in")
    public ResponseEntity<String> SignIn(@RequestParam("EMAIL") String email, @RequestParam("PASSWORD") String password){
        Profile profile =  profileService.SignIn(email, password);
        
        if(profile != null) {
            JSONObject res = new JSONObject();
            try{
                res.put("PROFILE", new ObjectMapper().writeValueAsString(profile));
                res.put("TOKEN", new ObjectMapper().writeValueAsString(tokenService.tokenUtils.createToken(profile)));
            } catch(Exception e){
                System.out.println(new Exception ("Error: couldn't create JSON for ", e));
            }
            
            return ResponseEntity.ok().body(res.toString());
        }
        return ResponseEntity.badRequest().body("Error: cannot get access to account");
    }
    @CrossOrigin
    @PostMapping("restore")
    public ResponseEntity<String> restore(@RequestParam("EMAIL") String email, @RequestParam("PASSWORD") String password){
        profileService.restore(email, password);
        return SignIn(email, password);
    }

    @CrossOrigin
    @PostMapping("verify")
    public ResponseEntity<String> verify(@RequestParam("TOKEN") String token){
        Boolean res = tokenService.tokenUtils.verifyToken(token);
        if(res) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
