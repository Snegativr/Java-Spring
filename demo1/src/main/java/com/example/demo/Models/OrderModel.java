package com.example.demo.Models;

import java.lang.annotation.Repeatable;


public class OrderModel {
    private Integer id;
    private String user;
    private String orderType;
    private String crypto;
    private Double amount;


    public OrderModel(Integer id, String user, String orderType, String crypto, Double amount) {
        this.id = id;
        this.user = user;
        this.orderType = orderType;
        this.crypto = crypto;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", orderType='" + orderType + '\'' +
                ", crypto='" + crypto + '\'' +
                ", amount=" + amount +
                '}';
    }
}
