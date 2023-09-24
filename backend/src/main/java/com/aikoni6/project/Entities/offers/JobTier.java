package com.aikoni6.project.Entities.offers;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "job_tiers")
public class JobTier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "";
    private String description= "";

    private Long next;
    private Long previous;
    private Long offer;
    private Long cost;
}
