package com.example.demo.Controllers;

import com.example.demo.Models.OrderModel;
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
    public List<OrderModel> orders = new ArrayList<>();
    private int idCounter = 1;

    @GetMapping("/getAll")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orders);
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
        order.setId(idCounter++);
        orders.add(order);
        return "redirect:/order/getAll";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        orders.removeIf(order -> order.getId() == id);
        return "redirect:/order/getAll";
    }
}
