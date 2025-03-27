package com.example.demo.aspects;


import com.example.demo.services.EmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class CryptoLoggingAspect {
    private static final Logger logger = Logger.getLogger(CryptoLoggingAspect.class.getName());

    private final EmailService emailService;

    public CryptoLoggingAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.Controllers.CryptoController.*(..))", returning = "result")
    public void logMethodArguments(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Метод: " + methodName + " | Аргументи: " + Arrays.toString(args) + " | Результат: " + result);
        emailService.sendInfo("CryptoLogger: ", "Метод: " + methodName + " | Аргументи: " + Arrays.toString(args) + " | Результат: " + result);
    }
}
