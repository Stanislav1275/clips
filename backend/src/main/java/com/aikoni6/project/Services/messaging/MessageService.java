package com.aikoni6.project.Services.messaging;

import com.aikoni6.project.Entities.messaging.Chat;
import com.aikoni6.project.Entities.messaging.Message;
import com.aikoni6.project.Repositories.messaging.ChatRepo;
import com.aikoni6.project.Repositories.messaging.MessageRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ChatRepo chatRepo;

    public List<Long> getMessagesIDByChatID(Long chatID){
        Optional<Chat> chatCheck = chatRepo.findById(chatID);
        if(chatCheck.isEmpty()) throw new RuntimeException("Error: chat " + chatID + "doesn't exists!");
        Chat chat = chatCheck.get();
        return getMessagesIDByChat(chat);
    }
    public List<Message> getMessagesByChatID(Long chatID){
        Chat chat = chatRepo.findById(chatID).orElse(null);
        if(chat == null) return null;
        return getMessagesByChat(chat);
    }
    public List<Long> getMessagesIDByChat(Chat chat){
        List<Message> messages = getMessagesByChat(chat);
        List <Long> messagesID = new ArrayList<>();
        for (Message message:
                messages) {
            messagesID.add(message.getId());
        }
        return messagesID;
    }
    public List<Message> getMessagesByChat(Chat chat){
        return messageRepo.findMessageByChat(chat.getId());
    }
    public Message getMessageByID(Long ID){
        return messageRepo.findById(ID).orElse(null);
    }
    public Message saveMessage(Message message){
        return messageRepo.save(message);
    }
}
