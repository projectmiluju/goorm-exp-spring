package com.example.practice.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {

    public String findMessage() {
        return "Hello, World! (from repository)";
    }
}
