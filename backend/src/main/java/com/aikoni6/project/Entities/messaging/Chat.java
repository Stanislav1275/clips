package com.aikoni6.project.Entities.messaging;

import com.aikoni6.project.Entities.general.Profile;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long profile1;
    private Long profile2;
    private Boolean isOnOffer = false;
}
