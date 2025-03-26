package com.example.demo.RestControllers;

import com.example.demo.Models.CryptoModel;
import com.example.demo.Models.OrderModel;
import com.example.demo.services.CryptoService;
import com.example.demo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/crypto")
public class CryptoRestController {


    private final CryptoService cryptoService;

    public CryptoRestController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping
    public List<CryptoModel> findAll() {
        return cryptoService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CryptoModel> addCrypto(@RequestBody Map<String, String> body) {
        try {
            CryptoModel cryptoModel = new CryptoModel();

            String crypto = body.get("crypto");
            double price = Double.valueOf(body.get("price"));


            cryptoModel.setCrypto(crypto);
            cryptoModel.setPrice(price);

            cryptoService.AddCrypto(cryptoModel);
            return ResponseEntity.ok(cryptoModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCrypto(@RequestBody Map<String, String> body) {
        try {

            CryptoModel cryptoModel = new CryptoModel();

            int id = Integer.valueOf(body.get("id"));
            String crypto = body.get("crypto");
            double price = Double.valueOf(body.get("price"));

            cryptoModel.setId(id);
            cryptoModel.setCrypto(crypto);
            cryptoModel.setPrice(price);

            cryptoService.updateCrypto(cryptoModel);
            return ResponseEntity.ok("Криптовалюта оновлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Помилка оновлення: " + e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteCrypto(@PathVariable Long id) {
        try {
            cryptoService.deleteCrypto(id);
            return ResponseEntity.ok("Криптовалюта видалена");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Помилка видалення: " + e.getMessage());
        }
    }
}
