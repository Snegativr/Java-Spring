package com.example.demo8.Controllers;

import com.example.demo8.client.AuthClient;
import com.example.demo8.client.CryptoClient;
import com.example.demo8.client.OrderClient;
import com.example.demo8.client.UserClient;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/webflux")
public class WebFluxController {
    private final UserClient userClient;
    private final OrderClient orderClient;
    private final CryptoClient cryptoClient;
    private final AuthClient authClient;

    public WebFluxController(UserClient userClient,OrderClient orderClient,CryptoClient cryptoClient,AuthClient authClient) {
        this.userClient = userClient;
        this.orderClient = orderClient;
        this.cryptoClient = cryptoClient;
        this.authClient = authClient;
    }

    @GetMapping("/api/user")
    public Mono<String> getUsers() {
        return userClient.getUsers()
                .onErrorReturn("Error in getUsers");
    }
    @PostMapping("/api/user/add")
    public Mono<String> addUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        return userClient.addUser(username, password);
    }

    @PostMapping("/api/user/update")
    public Mono<String> updateUser(@RequestBody Map<String, String> requestBody) {
        return userClient.updateUser(
                Long.valueOf(requestBody.get("id")),
                requestBody.get("username"),
                requestBody.get("password")
        );
    }

    @PostMapping("/api/user/delete/{id}")
    public Mono<String> deleteUser(@PathVariable Long id) {
        return userClient.deleteUser(id);
    }


    @GetMapping("/api/order")
    public Mono<String> getOrders() {
        return orderClient.getOrders()
                .onErrorReturn("Error in getOrders");
    }
    @PostMapping("/api/order/add")
    public Mono<String> addOrder(String user, String orderType, String crypto , String amount) {
        return orderClient.addOrder(user, orderType, crypto, amount);
    }

    @PostMapping("/api/order/update")
    public Mono<String> updateOrder(String newId,String user, String orderType, String crypto , String amount) {
        return orderClient.updateOrder(
                Long.valueOf(newId),
                user,
                orderType,
                crypto,
                amount
        );
    }

    @PostMapping("/api/order/delete/{id}")
    public Mono<String> deleteOrder(@PathVariable Long id) {
        return orderClient.deleteOrder(id);
    }

    @GetMapping("/api/crypto")
    public Mono<String> getCryptos() {
        return cryptoClient.getCryptos()
                .onErrorReturn("Error in getCryptos");
    }
    @PostMapping("/api/crypto/add")
    public Mono<String> addCrypto(String crypto, String price) {
        return cryptoClient.addCrypto(crypto, price);
    }

    @PostMapping("/api/crypto/update")
    public Mono<String> updateCrypto(String newId,String crypto, String price) {
        return cryptoClient.updateCrypto(
                Long.valueOf(newId),
                crypto,
                price
        );
    }

    @PostMapping("/api/crypto/delete/{id}")
    public Mono<String> deleteCrypto(@PathVariable Long id) {
        return cryptoClient.deleteCrypto(id);
    }


    @PostMapping("/api/auth/register")
    public Mono<String> register(@RequestBody Map<String, String> requestBody) {
        return authClient.register(requestBody.get("username"), requestBody.get("password"));
    }

    @PostMapping("/api/auth/login")
    public Mono<String> login(@RequestBody Map<String, String> requestBody) {
        return authClient.login(requestBody.get("username"), requestBody.get("password"));
    }
}
