## **AOP 적용 예제**

## ✅ 프로젝트 구조

```
src/main/java/com/example/springBase/
├── SpringBaseApplication.java
├── service/UserService.java
├── aspect/LoggingAspect.java
└── AppRunner.java
```

## ✅ 코드 작성

### 📄 `UserService.java` (타깃 클래스)

```java
package com.example.springBase.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void joinUser(String name) {
        System.out.println("회원 가입 처리 중: " + name);
    }
}

```

---

### 📄 `LoggingAspect.java` (애스펙트)

```java
package com.example.springBase.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 메서드 실행 전
    @Before("execution(* com.example.aopdemo.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[Before] 메서드 실행 전: " + joinPoint.getSignature().getName());
    }

    // 메서드 실행 후
    @After("execution(* com.example.aopdemo.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[After] 메서드 실행 후: " + joinPoint.getSignature().getName());
    }

    // 반환값 확인
    @AfterReturning(pointcut = "execution(* com.example.aopdemo.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AfterReturning] 반환값: " + result);
    }
}
```

---

### 📄 `AppRunner.java`

```java
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
```

---

### 📄 `AopDemoApplication.java`

```java
package com.example.springBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBaseApplication.class, args);
    }
}

```

---

## ✅ 실행 결과 예시 (콘솔)

```
[Before] 메서드 실행 전: joinUser
회원 가입 처리 중: 홍길동
[AfterReturning] 반환값: null
[After] 메서드 실행 후: joinUser
```

---

## ✅ 핵심 요약

| 항목 | 설명 |
| --- | --- |
| `@Aspect` | 애스펙트 클래스 정의 |
| `@Before` | 타깃 메서드 실행 전에 실행 |
| `@After` | 타깃 메서드 실행 후에 실행 |
| `@AfterReturning` | 정상 종료 후 반환값 로깅 가능 |
| 포인트컷 | `execution(* 패키지.클래스.메서드(..))` 형식으로 지정 |

---