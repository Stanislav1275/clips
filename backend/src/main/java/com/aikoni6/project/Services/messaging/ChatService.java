package com.aikoni6.project.Services.messaging;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Entities.messaging.Chat;
import com.aikoni6.project.Repositories.messaging.ChatRepo;
import com.aikoni6.project.Services.system.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChatService {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private TokenService tokenService;
    
    public List<Chat> getChatsByToken(String token){
        Profile profile = tokenService.getUserByToken(token);
        if(profile != null) return chatRepo.findChatByProfile1OrProfile2(profile.getId(), profile.getId());
        return null;
    }
    public Chat createChat(Profile user1, Profile user2){
        if(user1 == null || user2 == null) return null;
        Chat res = new Chat();
        res.setProfile1(user1.getId());
        res.setProfile2(user2.getId());
        return chatRepo.save(res);
    }
    public Chat createOrderChat(Profile user1, Profile user2){
        if(user1 == null || user2 == null) return null;
        Chat res = createChat(user1, user2);
        res.setIsOnOffer(true);
        return chatRepo.save(res);
    }
    public Boolean isInChat(Chat chat, Profile profile){
        return Objects.equals(chat.getProfile1(), profile.getId()) || Objects.equals(chat.getProfile2(), profile.getId());
    }
    public Chat getChatByID(Long id){
        if(id == null) return null;
        return chatRepo.findById(id).orElse(null);
    }
}
