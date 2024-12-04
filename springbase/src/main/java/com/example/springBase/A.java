package com.example.springBase;

import org.springframework.stereotype.Component;

@Component
public class A {

    private final B b;

    public A(B b) {
        this.b = b;
    }

    public void hello() {
        System.out.println("A가 호출됨");
    }
}