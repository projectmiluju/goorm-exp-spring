package com.example.springBase;

import com.example.springBase.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements CommandLineRunner {

    private final UserService userService;

    public AppRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.joinUser("홍길동");
    }
}
