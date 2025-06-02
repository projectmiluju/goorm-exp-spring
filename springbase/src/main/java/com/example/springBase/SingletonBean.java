package com.example.springBase;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // 생략해도 기본은 singleton
public class SingletonBean {

    public SingletonBean() {
        System.out.println("SingletonBean 생성됨: " + this);
    }

    @PostConstruct
    public void init() {
        System.out.println("SingletonBean 초기화 메서드 호출됨");
    }
}
