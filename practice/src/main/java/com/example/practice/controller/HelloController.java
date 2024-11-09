package com.example.practice.controller;

import com.example.practice.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
        System.out.println(">>> HelloController 생성됨");
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.getHelloMessage();
    }
}