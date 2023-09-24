package com.aikoni6.project.Controllers;

import com.aikoni6.project.Entities.messaging.Chat;
import com.aikoni6.project.Entities.messaging.Message;
import com.aikoni6.project.Repositories.messaging.MessageRepo;
import com.aikoni6.project.Services.messaging.ChatService;
import com.aikoni6.project.Services.messaging.MessageService;
import com.aikoni6.project.Services.system.TokenService;
import com.aikoni6.project.Entities.general.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController("messageController")
public class MessageController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;

    @CrossOrigin
    @GetMapping("messages/{message_id}")
    public ResponseEntity<String> getMessage(@PathVariable("message_id") Long messageID, @RequestParam("TOKEN") String token){
        Profile user = tokenService.getUserByToken(token);
        if(user != null){
            Message msg = messageService.getMessageByID(messageID);
            Chat chat = chatService.getChatByID(msg.getChat());
            if(chatService.isInChat(chat, user)) {
                Optional<Message> res = messageRepo.findById(messageID);
                return res.map(message -> {
                    try {
                        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(message));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).orElseGet(() -> ResponseEntity.badRequest().body("Error: couldn't find the message"));
            } else ResponseEntity.badRequest().body("Error: could not access the message");
        }

        return ResponseEntity.badRequest().body("Error: current user not found");
    }
    @SneakyThrows
    @CrossOrigin
    @GetMapping("messages")
    public ResponseEntity<String> getAllMessages(@RequestParam("CHAT") Long chatID, @RequestParam("TOKEN") String token){
        Profile user = tokenService.getUserByToken(token);
        if(user != null){
            Chat chat = chatService.getChatByID(chatID);
            List<Message> messages = messageService.getMessagesByChat(chat);
            return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(messages));
        }
        return ResponseEntity.badRequest().body("Error: current user not found");
    }
    @SneakyThrows
    @CrossOrigin
    @PostMapping("messages")
    public ResponseEntity<String> saveMessage(@RequestParam("TOKEN") String token, @RequestParam("CHAT") Message message){
        Profile user = tokenService.getUserByToken(token);
        if(user != null){
            Chat chat = chatService.getChatByID(message.getChat());
            if(chatService.isInChat(chat, user)) {
                message = messageRepo.save(message);
                if(message.getId() != null) return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(message));
            } else ResponseEntity.badRequest().body("Error: user are not in the chat");
        } return ResponseEntity.badRequest().body("Error: user not found");
    }
}
