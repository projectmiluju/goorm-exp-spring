package com.example.springBase;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class A {

    private final B b;

    public A(@Lazy B b) {
        this.b = b;
    }

    public void hello() {
        System.out.println("A가 호출됨");
        b.hello(); // b도 호출 확인
    }
}
