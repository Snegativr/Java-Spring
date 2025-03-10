package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {
    @GetMapping("/addUser")
    public String addUser() {
        return "User added";
    }

    @GetMapping("/getAllUsers")
    public String getAllUsers() {
        return "Users returned";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        return "User deleted";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "Not implemented yet";
    }
}
