package com.example.springBase;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {

    public PrototypeBean() {
        System.out.println("PrototypeBean 생성됨: " + this);
    }

    @PostConstruct
    public void init() {
        System.out.println("PrototypeBean 초기화 메서드 호출됨");
    }
}
