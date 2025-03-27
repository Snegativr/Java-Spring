package com.example.demo.Controllers;

import com.example.demo.Models.CryptoModel;
import com.example.demo.services.CryptoService;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/crypto")
public class CryptoController {
//    private List<CryptoModel> cryptos = new ArrayList<>();
//    private int idCounter = 1;

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/getAll")
    public String getAllCrypto(Model model) {
        model.addAttribute("cryptos", cryptoService.findAll());
        return "crypto-list";
    }

    @GetMapping("/add")
    public String ShowCryptoAddForm(Model model) {
        model.addAttribute("crypto", new CryptoModel());
        return "add-crypto";
    }

    @PostMapping("/add")
    public String addCrypto(@Valid @ModelAttribute("crypto") CryptoModel crypto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-crypto";
        }
//        crypto.setId(idCounter++);
        cryptoService.AddCrypto(crypto);
        return "redirect:/crypto/getAll";
    }

    @PostMapping("/delete/{id}")
    public String deleteCrypto(@PathVariable Long id) {
        cryptoService.deleteCrypto(id);
        return "redirect:/crypto/getAll";
    }

//    @PutMapping("/editCrypto/{id}")
//    public String editUser(@PathVariable int id,@RequestBody CryptoModel crypto) {
//        cryptos.set(id, crypto);
//        return "crypto changed" + crypto;
//    }
}