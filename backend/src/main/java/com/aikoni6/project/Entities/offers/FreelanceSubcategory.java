package com.aikoni6.project.Entities.offers;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subcategory")
public class FreelanceSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private Long category;

}
