package com.aikoni6.project.Entities.offers;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long tier;
    private Long profile;
    private Long chat;
    private String status;
}
