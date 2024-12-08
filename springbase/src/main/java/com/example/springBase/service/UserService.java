package com.example.springBase.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void joinUser(String name) {
        System.out.println("회원 가입 처리 중: " + name);
    }
}
