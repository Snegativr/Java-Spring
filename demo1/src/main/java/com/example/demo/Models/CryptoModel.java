package com.example.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "cryptos")
public class CryptoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Crypto can not be empty")
    private String crypto;

    @NotNull(message = "Crypto price can not be empty")
    private double price;

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }
    public String getCrypto() {
        return crypto;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CryptoModel{id=" + id + ", crypto='" + crypto + "', price=" + price + "}";
    }
}
