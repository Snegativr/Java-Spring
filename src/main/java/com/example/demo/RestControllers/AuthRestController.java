package com.example.demo.RestControllers;

import com.example.demo.Models.UserModel;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.UserService;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Користувач вже існує");
        }
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);
        userService.AddUser(user);
        return ResponseEntity.ok("Користувача зареєстровано");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);
        if (userService.authenticateUser(user)) {
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(401).body("Wrong login or password");
    }

    @PostMapping("/logout")
    public void logoutUser() {}
}
