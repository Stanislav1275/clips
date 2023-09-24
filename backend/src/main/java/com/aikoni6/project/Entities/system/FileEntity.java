package com.aikoni6.project.Entities.system;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String fullName;
    private String originalName;
    private String extension;
}
