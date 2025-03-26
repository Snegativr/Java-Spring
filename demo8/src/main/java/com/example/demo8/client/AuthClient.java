package com.example.demo8.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;


@Component
public class AuthClient {

    private final WebClient webClient;

    public AuthClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> register(String username, String password) {
        return webClient.post()
                .uri("/api/auth/register")
                .bodyValue(Map.of("username", username, "password", password))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> login(String username, String password) {
        return webClient.post()
                .uri("/api/auth/login")
                .bodyValue(Map.of("username", username, "password", password))
                .retrieve()
                .bodyToMono(String.class);
    }
}
