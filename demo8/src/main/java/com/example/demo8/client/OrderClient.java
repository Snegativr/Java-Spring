package com.example.demo8.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;


@Component
public class OrderClient {

    private final WebClient webClient;

    public OrderClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getOrders() {
        return webClient.get()
                .uri("/api/order")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> addOrder(String user, String orderType, String crypto , String amount) {
        return webClient.post()
                .uri("/api/order/add")
                .bodyValue(Map.of("user", user, "orderType", orderType, "crypto", crypto, "amount", amount))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> updateOrder(Long id, String user, String orderType, String crypto , String amount) {
        return webClient.post()
                .uri("/api/order/update")
                .bodyValue(Map.of("id", id.toString(),"user", user, "orderType", orderType, "crypto", crypto, "amount", amount))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> deleteOrder(Long id) {
        return webClient.post()
                .uri("/api/order/delete/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }
}
