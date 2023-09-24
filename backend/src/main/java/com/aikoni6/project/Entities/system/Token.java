package com.aikoni6.project.Entities.system;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

import com.aikoni6.project.Entities.general.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private Long profile;
    @JsonIgnore
    private OffsetDateTime expTime;

    private String publicPart;
    private String privatePart;

}
