package com.example.demo.aspects;


import com.example.demo.Models.OrderModel;
import com.example.demo.services.EmailService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderAspect {

    private final EmailService emailService;

    public OrderAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @Before("execution(* com.example.demo.repositories.OrderRepository.save(..)) && args(order)")
    public void setDefaultVotingPeriod(OrderModel order) {
        if (order.getOrderType() == null || order.getOrderType().isEmpty()) {
            order.setOrderType("BUY");
            emailService.sendInfo("OrderAspect: ", "Order aspect has been saved");
        }
    }
}
