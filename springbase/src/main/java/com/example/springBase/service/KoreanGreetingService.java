package com.example.springBase.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 기본 주입 대상
public class KoreanGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "안녕하세요! (Korean)";
    }
}
