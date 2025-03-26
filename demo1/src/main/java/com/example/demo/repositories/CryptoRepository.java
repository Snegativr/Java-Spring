package com.example.demo.repositories;

import com.example.demo.Models.CryptoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<CryptoModel, Long> {

}
