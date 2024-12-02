package com.example.springBase.controller;

import com.example.springBase.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingController {

    // @Primary가 붙은 KoreanGreetingService 주입
    @Autowired
    private GreetingService defaultService;

    // @Qualifier로 EnglishGreetingService 명시 주입
    @Autowired
    @Qualifier("englishGreetingService")
    private GreetingService englishService;

    public void printGreetings() {
        System.out.println("기본 인사: " + defaultService.greet());
        System.out.println("영어 인사: " + englishService.greet());
    }
}
