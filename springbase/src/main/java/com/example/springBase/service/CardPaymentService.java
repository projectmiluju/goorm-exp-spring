package com.example.springBase.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // 기본 주입 대상
public class CardPaymentService implements PaymentService {

    @Override
    public String pay(int amount) {
        return "카드로 " + amount + "원 결제 완료";
    }
}