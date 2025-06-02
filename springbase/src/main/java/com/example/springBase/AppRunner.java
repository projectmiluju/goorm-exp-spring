package com.example.springBase;

import com.example.springBase.service.PaymentProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements CommandLineRunner {

    private final PaymentProcessor processor;

    public AppRunner(PaymentProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void run(String... args) throws Exception {
        processor.process(10000);
    }
}
