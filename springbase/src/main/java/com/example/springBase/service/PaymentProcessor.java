package com.example.springBase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    private final PaymentService paymentService;

    @Autowired
    public PaymentProcessor(@Qualifier("kakaoPay") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void process(int amount) {
        String result = paymentService.pay(amount);
        System.out.println(result);
    }
}
