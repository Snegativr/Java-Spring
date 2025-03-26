package com.example.demo.services;

import com.example.demo.Models.UserModel;
import com.example.demo.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel AddUser(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean authenticateUser(@Valid @ModelAttribute("user") UserModel user) {
        Optional<UserModel> userOpt = userRepository.findByUsername(user.getUsername());
        return userOpt.map(userModel -> userModel.getPassword().equals(user.getPassword())).orElse(false);
    }
}
