package com.example.demo.aspects;


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

    @AfterReturning(pointcut = "execution(* com.example.demo.Controllers.CryptoController.*(..))", returning = "result")
    public void logMethodArguments(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Метод: " + methodName + " | Аргументи: " + Arrays.toString(args) + " | Результат: " + result);
    }
}
