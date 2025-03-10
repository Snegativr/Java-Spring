package com.example.demo.Controllers;

import com.example.demo.Models.OrderModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    private Map<Integer, OrderModel> orders = new HashMap<>();
    Integer idCounter = 1;
    @PostMapping("/createOrder")
    public String createOrder(String user, String orderType, String crypto, Double amount) {
        OrderModel newOrder = new OrderModel(idCounter++,user,orderType,crypto,amount);
        orders.put(newOrder.getId(), newOrder);
        return "order created" + newOrder;
    }

    @GetMapping("/showAllOrders")
    public String showAllOrders() {
        return "orders = " + orders.values();
    }

    @GetMapping("/removeOrder/{id}")
    public String removeOrder(@PathVariable Integer id) {
        orders.remove(id);
        return "order removed";
    }
}
