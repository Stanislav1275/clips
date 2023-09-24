package com.aikoni6.project.Repositories.messaging;

import com.aikoni6.project.Entities.messaging.Chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat, Long> {
    public List<Chat> findChatByProfile1OrProfile2(Long profile1, Long profile2);
}
