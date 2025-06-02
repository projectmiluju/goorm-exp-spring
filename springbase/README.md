## ✅ 실습 목표

- `@Scope` 애너테이션으로 웹 스코프 지정
- `request`, `session`, `application` 스코프 빈 생성
- 각각의 생명주기를 로그로 확인
- 브라우저 요청을 반복하면서 동작 확인

---

## ✅ 사전 준비

### 🔧 의존성 확인

`build.gradle`에 다음 의존성이 포함되어 있어야 합니다:

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
}

```

---

## ✅ 1단계: 스코프별 빈 생성

### 📄 `RequestScopeBean.java`

```java
package com.example.springBase.scopebean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeBean {
    @PostConstruct
    public void init() {
        System.out.println("🟢 RequestScopeBean 생성됨: " + this);
    }

    public String getScopeName() {
        return "Request Scope";
    }
}
```

---

### 📄 `SessionScopeBean.java`

```java
package com.example.springBase.scopebean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopeBean {
    @PostConstruct
    public void init() {
        System.out.println("🟡 SessionScopeBean 생성됨: " + this);
    }

    public String getScopeName() {
        return "Session Scope";
    }
}

```

---

### 📄 `ApplicationScopeBean.java`

```java
package com.example.springBase.scopebean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationScopeBean {
    @PostConstruct
    public void init() {
        System.out.println("🔵 ApplicationScopeBean 생성됨: " + this);
    }

    public String getScopeName() {
        return "Application Scope";
    }
}

```

---

## ✅ 2단계: 컨트롤러 생성

### 📄 `ScopeController.java`

```java
package com.example.springBase.controller;

import com.example.springBase.scopebean.ApplicationScopeBean;
import com.example.springBase.scopebean.RequestScopeBean;
import com.example.springBase.scopebean.SessionScopeBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScopeController {

    private final RequestScopeBean requestBean;
    private final SessionScopeBean sessionBean;
    private final ApplicationScopeBean applicationBean;

    public ScopeController(RequestScopeBean requestBean,
                           SessionScopeBean sessionBean,
                           ApplicationScopeBean applicationBean) {
        this.requestBean = requestBean;
        this.sessionBean = sessionBean;
        this.applicationBean = applicationBean;
    }

    @GetMapping("/scope")
    public String scopeInfo(Model model) {
        model.addAttribute("request", requestBean.getScopeName() + " : " + requestBean);
        model.addAttribute("session", sessionBean.getScopeName() + " : " + sessionBean);
        model.addAttribute("application", applicationBean.getScopeName() + " : " + applicationBean);
        return "scope-view";
    }
}
```

---

## ✅ 3단계: 뷰 작성

### 📄 `src/main/resources/templates/scope-view.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head><title>스코프 테스트</title></head>
<body>
<h1>빈 스코프 테스트</h1>
<p th:text="${request}"></p>
<p th:text="${session}"></p>
<p th:text="${application}"></p>
</body>
</html>
```

---

## ✅ 4단계: 실행 및 테스트

1. 서버 실행: `./gradlew bootRun`
2. 브라우저에서 `http://localhost:8080/scope` 접속
3. 새로고침하거나 새 창에서 접속하며 로그 확인

---

## ✅ 로그 예시

```
🟢 RequestScopeBean 생성됨: com.example.springBase.scopebean.RequestScopeBean@3e275fd0
🟡 SessionScopeBean 생성됨: com.example.springBase.scopebean.SessionScopeBean@136e64eb
🔵 ApplicationScopeBean 생성됨: com.example.springBase.scopebean.ApplicationScopeBean@24c75faa
```

---

## ✅ 동작 요약

| 스코프 종류 | 생성 시점 | 유지 범위 |
| --- | --- | --- |
| `request` | 매 요청마다 새로 생성 | 1 HTTP 요청 |
| `session` | 최초 세션 요청 시 한 번 생성 | 브라우저 세션 |
| `application` | 애플리케이션 시작 시 한 번 생성 | 전체 애플리케이션 공통 |