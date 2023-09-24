package com.aikoni6.project.Entities.messaging;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long messageCreator;

    private Long chat;
    private String message;
    private Long referenceList = null;
}
