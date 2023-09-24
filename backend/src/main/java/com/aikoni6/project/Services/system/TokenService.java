package com.aikoni6.project.Services.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Utils.TokenUtils;

@Service
public class TokenService {
    @Autowired
    public TokenUtils tokenUtils;
    
    public Profile getUserByToken(String token){
        return tokenUtils.getProfileByToken(tokenUtils.findToken(token));
    }
}
