package com.example.demo.repositories;

import com.example.demo.Models.OrderModel;
import com.example.demo.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
