package com.example.demo.services;

import com.example.demo.Models.OrderModel;
import com.example.demo.Models.UserModel;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void updateOrder(OrderModel order) {
        Optional<OrderModel> orderOpt = orderRepository.findById((long) order.getId());
        if (orderOpt.isPresent()) {
            OrderModel orderModel = orderOpt.get();
            orderModel.setUser(order.getUser());
            orderModel.setOrderType(order.getOrderType());
            orderModel.setCrypto(order.getCrypto());
            orderModel.setAmount(order.getAmount());
            orderRepository.save(orderModel);
        }
    }
}
