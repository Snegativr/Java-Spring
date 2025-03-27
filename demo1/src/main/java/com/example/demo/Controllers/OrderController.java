package com.example.demo.Controllers;

import com.example.demo.Models.OrderModel;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
//    public List<OrderModel> orders = new ArrayList<>();
//    private int idCounter = 1;


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/getAll")
    public String getAllOrders(Model model) {
        model.addAttribute("orders",orderService.findAll());
        return "order-list";
    }

    @GetMapping("/add")
    public String ShowOrderAddForm(Model model) {
        model.addAttribute("order", new OrderModel());
        return "add-order";
    }

    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute("order") OrderModel order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-order";
        }
//        order.setId(idCounter++);
        orderService.AddOrder(order);
        return "redirect:/order/getAll";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/order/getAll";
    }
}
