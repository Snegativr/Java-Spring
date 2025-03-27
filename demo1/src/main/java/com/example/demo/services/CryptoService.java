package com.example.demo.services;

import com.example.demo.Models.CryptoModel;
import com.example.demo.Models.OrderModel;
import com.example.demo.Models.UserModel;
import com.example.demo.repositories.CryptoRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void updateCrypto(CryptoModel crypto) {
        Optional<CryptoModel> cryptoOpt = cryptoRepository.findById((long) crypto.getId());
        if (cryptoOpt.isPresent()) {
            CryptoModel cryptoModel = cryptoOpt.get();
            cryptoModel.setCrypto(crypto.getCrypto());
            cryptoModel.setPrice(crypto.getPrice());
            cryptoRepository.save(cryptoModel);
        }
    }
}
