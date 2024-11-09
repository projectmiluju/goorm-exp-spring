package com.example.practice.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {

    public HelloRepository() {
        System.out.println(">>> HelloRepository 생성됨");
    }

    public String findMessage() {
        return "Hello, World! (from repository)";
    }
}
