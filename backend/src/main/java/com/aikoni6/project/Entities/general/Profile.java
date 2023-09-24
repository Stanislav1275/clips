package com.aikoni6.project.Entities.general;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "";
    private String surname = "";
    @JsonIgnore
    private String email;
    private String nickname = "";
    @JsonIgnore
    private String password;
    private String moDo = "";
    private Boolean isDeleted = false;
    private Boolean isPrivate = false;

    private Long profilePic;
}
