package com.example.springBase;

import org.springframework.stereotype.Component;

@Component
public class B {

    private final A a;

    public B(A a) {
        this.a = a;
    }

    public void hello() {
        System.out.println("B가 호출됨");
    }
}
