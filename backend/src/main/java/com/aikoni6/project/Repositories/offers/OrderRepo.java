package com.aikoni6.project.Repositories.offers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.aikoni6.project.Entities.offers.Order;

public interface OrderRepo extends JpaRepository<Order, java.lang.Long> {
    public List<Order> findByProfile(Long user);
    public List<Order> findByTier(Long tier);
    public List<Order> findByStatus(String status);
}
