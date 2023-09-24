package com.aikoni6.project.Repositories.messaging;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.messaging.Message;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    public List<Message> findMessageByChat(Long chat);
}
