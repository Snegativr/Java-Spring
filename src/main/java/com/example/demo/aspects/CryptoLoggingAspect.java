package com.example.demo.aspects;


import com.example.demo.services.EmailService;
import com.example.demo.services.WebSocketService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class CryptoLoggingAspect {
    private static final Logger logger = Logger.getLogger(CryptoLoggingAspect.class.getName());

    private final EmailService emailService;
    private final WebSocketService webSocketService;

    public CryptoLoggingAspect(EmailService emailService, WebSocketService webSocketService) {
        this.emailService = emailService;
        this.webSocketService = webSocketService;
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.Controllers.CryptoController.*(..))", returning = "result")
    public void logMethodArguments(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Метод: " + methodName + " | Аргументи: " + Arrays.toString(args) + " | Результат: " + result);

//        emailService.sendInfo("CryptoLogger: ", "Метод: " + methodName + " | Аргументи: " + Arrays.toString(args) + " | Результат: " + result);

        String message = "Метод: " + methodName + " | Аргументи: " + Arrays.toString(args) + " | Результат: " + result;

        webSocketService.sendAspectInfo("CryptoLogger", message);

    }
}
