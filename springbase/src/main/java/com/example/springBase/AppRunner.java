package com.example.springBase;

import com.example.springBase.controller.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private GreetingController controller;

    @Override
    public void run(String... args) throws Exception {
        controller.printGreetings();
    }
}
