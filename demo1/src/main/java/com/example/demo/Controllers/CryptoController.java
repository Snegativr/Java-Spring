package com.example.demo.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CryptoController {
    private List<String> cryptos = new ArrayList<>();

    @GetMapping("/addCrypto")
    public String addCrypto(String crypto) {
        cryptos.add(crypto);
        return crypto + " added";
    }

    @GetMapping("/getAll")
    public List<String> getAllCrypto() {
        return cryptos;
    }

    @GetMapping("/deleteCrypto")
    public String deleteCrypto(String crypto) {
        cryptos.remove(crypto);
        return crypto + " deleted";
    }

    @GetMapping("/edit")
    public String editCrypto(String oldCrypto,String newCrypto) {
        cryptos.set(cryptos.indexOf(oldCrypto), newCrypto);
        return oldCrypto + " changed to " + newCrypto;
    }
}
