package com.example.demo.aspects;


import com.example.demo.Models.OrderModel;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderAspect {

    @Before("execution(* com.example.demo.repositories.OrderRepository.save(..)) && args(order)")
    public void setDefaultVotingPeriod(OrderModel order) {
        if (order.getOrderType() == null || order.getOrderType().isEmpty()) {
            order.setOrderType("BUY");
        }
    }
}
