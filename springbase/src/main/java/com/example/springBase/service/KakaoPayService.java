package com.example.springBase.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("kakaoPay")
public class KakaoPayService implements PaymentService {

    @Override
    public String pay(int amount) {
        return "카카오페이로 " + amount + "원 결제 완료";
    }
}
