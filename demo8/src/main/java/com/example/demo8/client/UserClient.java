package com.example.demo8.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getUsers() {
        return webClient.get()
                .uri("/api/user")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> addUser(String username, String password) {
        return webClient.post()
                .uri("/api/user/add")
                .bodyValue(Map.of("username", username, "password", password))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> updateUser(Long id, String username, String password) {
        return webClient.post()
                .uri("/api/user/update")
                .bodyValue(Map.of("id", id.toString(), "username", username, "password", password))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> deleteUser(Long id) {
        return webClient.post()
                .uri("/api/user/delete/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }
}
