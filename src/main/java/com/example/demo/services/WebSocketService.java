package com.example.demo.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendAspectInfo(String aspectName, String message) {
        String path = "/topic/" + aspectName.toLowerCase();
        System.out.println("Sending message to: " + path + " | " + message);
        messagingTemplate.convertAndSend(path, message);
    }

}
