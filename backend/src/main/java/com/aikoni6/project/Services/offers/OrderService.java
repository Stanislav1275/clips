package com.aikoni6.project.Services.offers;

import com.aikoni6.project.Entities.general.Profile;
import com.aikoni6.project.Entities.offers.Order;
import com.aikoni6.project.Repositories.offers.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getOrderByProfile(Profile profile) {
        return orderRepo.findByProfile(profile.getId());
    }
    public Order saveOrder(Order order){
        if(order == null) return null;
        return orderRepo.save(order);
    }
    public Order getByID(Long ID){
        return orderRepo.findById(ID).orElse(null);
    }
}
