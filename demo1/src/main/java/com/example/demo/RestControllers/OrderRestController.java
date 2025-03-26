package com.example.demo.RestControllers;

import com.example.demo.Models.OrderModel;
import com.example.demo.Models.UserModel;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderModel> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<OrderModel> addOrder(String user, String orderType, String crypto , String amount) {
        try {
            OrderModel order = new OrderModel();
            order.setUser(user);
            order.setOrderType(orderType);
            order.setCrypto(crypto);
            double newAmount = Double.valueOf(amount);
            order.setAmount(newAmount);

            orderService.AddOrder(order);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateOrder(String newId,String user, String orderType, String crypto , String amount) {
        try {
            int id = Integer.valueOf(newId);
            OrderModel order = new OrderModel();
            order.setId(id);
            order.setUser(user);
            order.setOrderType(orderType);
            order.setCrypto(crypto);

            double newAmount = Double.valueOf(amount);
            order.setAmount(newAmount);


            orderService.updateOrder(order);
            return ResponseEntity.ok("Заказ оновлено");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Помилка оновлення: " + e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Заказ видалено");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Помилка видалення: " + e.getMessage());
        }
    }
}
