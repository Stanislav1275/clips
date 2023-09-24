package com.aikoni6.project.Controllers;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Entities.messaging.Chat;
import com.aikoni6.project.Services.messaging.ChatService;
import com.aikoni6.project.Services.system.TokenService;
import com.aikoni6.project.Services.general.ProfileService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("chat")
public class ChatController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ProfileService profileService;

    @SneakyThrows
    @CrossOrigin
    @GetMapping("Chat")
    public ResponseEntity<String> getChats(@RequestParam("TOKEN") String token){
        List<Chat> chats = chatService.getChatsByToken(token);
        if(chats != null) return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(chats));
        return ResponseEntity.badRequest().build();
    }
    @SneakyThrows
    @CrossOrigin
    @PostMapping("Chats")
    public ResponseEntity<String> createChat(@RequestParam("USER_ID") Long userID, @RequestParam("TOKEN") String token){
        Profile user = profileService.getProfileByID(userID);
        if(user == null || user.getIsDeleted()) return ResponseEntity.badRequest().body("Error: user was not found");
        Profile author = tokenService.getUserByToken(token);
        if(author == null || author.getIsDeleted()) return ResponseEntity.badRequest().body("Error: author was not found");
        if(profileService.canContact(author, user)) return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(chatService.createChat(author,user)));
        return ResponseEntity.badRequest().build();
    }
}
