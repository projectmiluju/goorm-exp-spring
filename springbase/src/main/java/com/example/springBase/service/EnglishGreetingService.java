package com.example.springBase.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("englishGreetingService") // 이름 지정
public class EnglishGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Hello! (English)";
    }
}