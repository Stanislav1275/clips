package com.aikoni6.project.Entities.offers;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer mark;

    private Long onOrder;
    private String review = "";
}
