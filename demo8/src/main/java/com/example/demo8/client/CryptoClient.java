package com.example.demo8.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class CryptoClient {

    private final WebClient webClient;

    public CryptoClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getCryptos() {
        return webClient.get()
                .uri("/api/crypto")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> addCrypto(String crypto, String price) {
        return webClient.post()
                .uri("/api/crypto/add")
                .bodyValue(Map.of("crypto", crypto, "price", price))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> updateCrypto(Long id, String crypto, String price) {
        return webClient.post()
                .uri("/api/crypto/update")
                .bodyValue(Map.of("id", id.toString(),"crypto", crypto, "price", price))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> deleteCrypto(Long id) {
        return webClient.post()
                .uri("/api/crypto/delete/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }
}
