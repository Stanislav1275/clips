package com.aikoni6.project.Entities.general;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long author;
    private String title;
    private String description;
    private String contentType;
    private Long contentID;
    private String state;
}
