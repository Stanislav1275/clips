package com.aikoni6.project.Entities.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ref_list")
public class ReferenceList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private Long nextRef;
    @JsonIgnore
    private Long previousRef;
    private String contentType;
    private Long contentID;
}
