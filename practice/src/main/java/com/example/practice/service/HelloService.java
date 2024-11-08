package com.example.practice.service;

import com.example.practice.repository.HelloRepository;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    public HelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    public String getHelloMessage() {
        return helloRepository.findMessage();
    }
}
