package com.example.demo.RestControllers;

import com.example.demo.Models.OrderModel;
import com.example.demo.Models.UserModel;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<OrderModel> addOrder(@RequestBody Map<String, String> body) {
        try {
            OrderModel order = new OrderModel();
            String user = body.get("user");
            String orderType = body.get("orderType");
            String crypto = body.get("crypto");
            double amount = Double.valueOf(body.get("amount"));

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
    public ResponseEntity<String> updateOrder(@RequestBody Map<String, String> body) {
        try {
            int id = Integer.valueOf(body.get("id"));
            String user = body.get("user");
            String orderType = body.get("orderType");
            String crypto = body.get("crypto");
            double amount = Double.valueOf(body.get("amount"));

            OrderModel order = new OrderModel();
            order.setId(id);
            order.setUser(user);
            order.setOrderType(orderType);
            order.setCrypto(crypto);
            order.setAmount(amount);


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
