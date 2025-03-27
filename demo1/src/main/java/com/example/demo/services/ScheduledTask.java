package com.example.demo.services;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {


    @Scheduled(initialDelay = 17, fixedRate = 190, timeUnit = TimeUnit.SECONDS)
    public void printInfo() {
        System.out.println("API для біржі криптовалют");
    }
}
