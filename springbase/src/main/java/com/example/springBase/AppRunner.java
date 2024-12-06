package com.example.springBase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final ApplicationContext context;

    public AppRunner(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {
        System.out.println("==== SingletonBean 테스트 ====");
        SingletonBean singleton1 = context.getBean(SingletonBean.class);
        SingletonBean singleton2 = context.getBean(SingletonBean.class);
        System.out.println("singleton1 == singleton2: " + (singleton1 == singleton2));

        System.out.println("==== PrototypeBean 테스트 ====");
        PrototypeBean proto1 = context.getBean(PrototypeBean.class);
        PrototypeBean proto2 = context.getBean(PrototypeBean.class);
        System.out.println("proto1 == proto2: " + (proto1 == proto2));
    }
}
