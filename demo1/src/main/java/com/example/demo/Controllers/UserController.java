package com.example.demo.Controllers;

import com.example.demo.Models.OrderModel;
import com.example.demo.Models.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final List<UserModel> users = new ArrayList<>();
    private int idCounter = 1;

    @GetMapping("/getAll")
    public String getAllUsers(Model model) {
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/add")
    public String ShowUserAddForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") UserModel user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        user.setId(idCounter++);
        users.add(user);
        return "redirect:/user/getAll";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        users.removeIf(user -> user.getId() == id);
        return "redirect:/user/getAll";
    }
}

