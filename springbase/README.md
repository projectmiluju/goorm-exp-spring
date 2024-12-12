## 실습 목표

- Spring MVC의 구성요소 (`@Controller`, `Model`, `View`)를 활용
- 사용자 요청을 받아 데이터를 전달하고 HTML 페이지에 출력
- 브라우저에서 정상적으로 렌더링되는지 확인

---

## ✅ 1단계: 간단한 도메인 객체 만들기

### 📄 `User.java`

```java
package com.example.springBase.model;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getter
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

```

> model/ 디렉터리는 MVC의 M(Model)에 해당하는 데이터 객체를 보관합니다.
>

---

## ✅ 2단계: 컨트롤러 작성

### 📄 `UserController.java`

```java
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

```

> controller/ 디렉터리는 Spring MVC의 C(Controller)를 담당합니다.
>

---

## ✅ 3단계: 뷰 작성 (Thymeleaf)

### 📄 `src/main/resources/templates/user.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>사용자 정보</title>
</head>
<body>
  <h2>사용자 정보</h2>
  <p>이름: <span th:text="${user.name}">이름</span></p>
  <p>이메일: <span th:text="${user.email}">이메일</span></p>
</body>
</html>
```

---

## ✅ 4단계: 실행 및 확인

1. 서버 실행: `./gradlew bootRun` 또는 IDE에서 Run
2. 브라우저에서 접속: `http://localhost:8080/user`

### 📸 브라우저 화면 (제출용 스크린샷 예시):

```
사용자 정보
이름: 홍길동
이메일: hong@example.com
```

---

## ✅ 정리

| MVC 구성 요소 | 설명 |
| --- | --- |
| Model | `User` 객체 – 사용자 정보를 담는 데이터 |
| View | `user.html` – 사용자 정보 출력 화면 |
| Controller | `UserController` – 데이터 전달 및 라우팅 |