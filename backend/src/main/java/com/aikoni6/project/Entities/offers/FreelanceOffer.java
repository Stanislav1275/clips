package com.aikoni6.project.Entities.offers;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "offers")
public class FreelanceOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Boolean isPrivate = false;

    private Long creator;
    private Long subcategory;
    private String text;
}
