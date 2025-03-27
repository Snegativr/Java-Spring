package com.example.demo.RestControllers;


import com.example.demo.Models.UserModel;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> addUser(@RequestBody Map<String, String> body) {
        try {
            UserModel user = new UserModel();
            String username = body.get("username");
            String password = body.get("password");
            user.setUsername(username);
            user.setPassword(password);

            userService.AddUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Map<String, String> body) {
        try {
            int id = Integer.valueOf(body.get("id"));
            String username = body.get("username");
            String password = body.get("password");
            UserModel user = new UserModel();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);

            userService.updateUser(user);
            return ResponseEntity.ok("Користувача оновлено");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Помилка оновлення: " + e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Користувача видалено");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Помилка видалення: " + e.getMessage());
        }
    }
}
