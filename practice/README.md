# 🧪 Spring Boot Practice

Goorm 풀스택 13회차 백엔드 Exp Mission      
Spring Boot 학습을 위한 실습 프로젝트입니다.  
간단한 REST API부터 MVC 구조, DI, JPA, 트랜잭션, AOP 등 핵심 개념을 단계별로 익힙니다.

---

## 🚀 프로젝트 실행 방법

### ✅ 환경

- Java 17
- Gradle 8+
- Spring Boot 3.4.5


---

##  AOP + 로깅 기능 적용

Spring AOP를 이용해 서비스 메서드의 실행 전후에 자동으로 로그를 출력하는 기능을 구현합니다.        
중복되는 로깅 코드를 제거하고, 핵심 로직과 공통 기능을 분리합니다.

### 목적

- 메서드 실행 전/후 시간을 로그로 기록
- Service 계층의 핵심 로직과 로그를 분리
- Cross-Cutting Concern(횡단 관심사)을 AOP로 처리

---

### 1. 프로젝트 구조

```markdown
com.example.practice
├── aop
│   └── LoggingAspect.java ← AOP 로깅 기능 추가
├── service
    └── PostService.java
```

---

### 2. 사용 기술 및 어노테이션

| 구성 요소         | 주요 어노테이션                                            |
|---------------|-----------------------------------------------------|
| LoggingAspect | @Aspect, @Component|

---

### 3. LoggingAspect 구현

```java
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.practice.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("▶️ [START] " + methodName);

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();  // 원래 메서드 실행
        long end = System.currentTimeMillis();

        System.out.println("✅ [END] " + methodName + " (" + (end - start) + " ms)");
        return result;
    }
}
```

### 4. 테스트 시나리오

```http request
POST /posts
{
  "title": "aop test",
  "content": "로깅 테스트"
}
```

```scss
▶️ [START] PostService.createPost(..)
✅ [END] PostService.createPost(..) (12 ms)
```

---

### 5. 학습 포인트 요약

| 개념                    | 설명                       |
| --------------------- | ------------------------ |
| `@Aspect`             | AOP 클래스를 정의할 때 사용        |
| `@Around`             | 메서드 실행 전후에 로직 삽입         |
| `ProceedingJoinPoint` | 실제 실행 대상 메서드 정보와 제어를 제공  |
| `execution(..)`       | AOP 적용 범위를 지정하는 포인트컷 표현식 |


---