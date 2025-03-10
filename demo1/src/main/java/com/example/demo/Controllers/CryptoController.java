package com.example.demo.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CryptoController {
    private Map<String, Double> cryptos = new HashMap<>();

    @PostMapping("/addCrypto")
    public String addCrypto(String crypto, double price) {
        cryptos.put(crypto, price);
        return crypto + " added";
    }

    @GetMapping("/getAll")
    public List<String> getAllCrypto() {
        List<String> AllCryptos = new ArrayList<>();

        for (Map.Entry<String, Double> entry : cryptos.entrySet()) {
            AllCryptos.add(entry.getKey() + ": $" + entry.getValue());
        }
        return AllCryptos;
    }

    @GetMapping("/deleteCrypto")
    public String deleteCrypto(String crypto) {
        cryptos.remove(crypto);
        return crypto + " deleted";
    }

    @GetMapping("/edit")
    public String editCrypto(String oldCrypto,String newCrypto) {
        return "Not implemented yet";
    }
}
