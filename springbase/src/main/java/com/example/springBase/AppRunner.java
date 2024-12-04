package com.example.springBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private A a;

    @Override
    public void run(String... args) throws Exception {
        a.hello();
    }
}
