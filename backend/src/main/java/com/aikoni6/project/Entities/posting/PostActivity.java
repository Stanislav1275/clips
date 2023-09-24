package com.aikoni6.project.Entities.posting;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class PostActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long post;
    private String activity;
    private Long refList;
}
