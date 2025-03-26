package com.example.demo.services;

import com.example.demo.Models.OrderModel;
import com.example.demo.Models.UserModel;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    public OrderModel AddOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
