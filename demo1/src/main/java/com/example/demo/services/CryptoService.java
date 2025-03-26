package com.example.demo.services;

import com.example.demo.Models.CryptoModel;
import com.example.demo.Models.UserModel;
import com.example.demo.repositories.CryptoRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;

    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public List<CryptoModel> findAll() {
        return cryptoRepository.findAll();
    }

    public CryptoModel AddCrypto(CryptoModel crypto) {
        return cryptoRepository.save(crypto);
    }

    public void deleteCrypto(Long id) {
        cryptoRepository.deleteById(id);
    }
}
