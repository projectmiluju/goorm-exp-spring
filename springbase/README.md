## 실습 목표

- 계층 구조: `Controller → Service → Repository`
- 간단한 사용자 목록 조회 기능 구현
- 의존성 주입을 통해 계층 간 연결
- 웹 페이지에서 사용자 리스트 출력

---

## ✅ 1단계: 디렉터리 구조

```
src/main/java/com/example/springBase/
├── controller/
│   └── UserController.java
├── service/
│   └── UserService.java
├── repository/
│   └── UserRepository.java
├── model/
│   └── User.java
└── SpringBaseApplication.java
```

---

## ✅ 2단계: 도메인 클래스 (Model)

### 📄 `User.java`

```java
package com.example.springBase.model;

public class User {
    private Long id;
    private String name;
    private String email;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
```

---

## ✅ 3단계: Repository 계층

### 📄 `UserRepository.java`

```java
package com.example.springBase.repository;

import com.example.springBase.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> findAll() {
        return Arrays.asList(
                new User(1L, "홍길동", "hong@example.com"),
                new User(2L, "김영희", "kim@example.com")
        );
    }
}

```

---

## ✅ 4단계: Service 계층

### 📄 `UserService.java`

```java
package com.example.springBase.service;

import com.example.springBase.model.User;
import com.example.springBase.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

```

---

## ✅ 5단계: Controller 계층

### 📄 `UserController.java`

```java
package com.example.springBase.controller;

import com.example.springBase.model.User;
import com.example.springBase.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }
}

```

---

## ✅ 6단계: View 작성 (Thymeleaf)

### 📄 `src/main/resources/templates/user-list.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>사용자 목록</title>
</head>
<body>
<h1>사용자 목록</h1>
<ul>
  <li th:each="user : ${users}">
    <span th:text="${user.name}">이름</span> -
    <span th:text="${user.email}">이메일</span>
  </li>
</ul>
</body>
</html>

```

---

## ✅ 7단계: 실행 및 확인

1. 서버 실행 후
2. 브라우저에서 `http://localhost:8080/users` 접속

### ✅ 결과 화면 예시 (스크린샷용):

```
홍길동 - hong@example.com
김영희 - kim@example.com
```

---

## ✅ 계층 구조 요약

| 계층 | 클래스 | 역할 |
| --- | --- | --- |
| Controller | `UserController` | 요청 처리, 응답 전달 |
| Service | `UserService` | 비즈니스 로직 |
| Repository | `UserRepository` | 데이터 제공 (DB 대역) |
| View | `user-list.html` | 사용자에게 HTML로 결과 출력 |