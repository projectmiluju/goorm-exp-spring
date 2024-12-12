package com.example.springBase.controller;

import com.example.springBase.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage(Model model) {
        User user = new User("홍길동", "hong@example.com");
        model.addAttribute("user", user); // 모델에 데이터 등록
        return "user"; // templates/user.html 렌더링
    }
}
