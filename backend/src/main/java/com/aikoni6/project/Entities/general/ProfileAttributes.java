package com.aikoni6.project.Entities.general;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profile_attributes")
public class ProfileAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean isBlocked;
    private Boolean isFollowing;
    private Boolean isFavourite;

    private Long profile;

    private Long forProfile;
}
