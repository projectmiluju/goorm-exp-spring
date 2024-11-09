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

## 🧪 Spring Boot - MVC + DI 구조 요약

### 1. 📁 프로젝트 구조

```markdown
com.example.practice  
├── controller  
│   └── HelloController.java  
├── service  
│   └── HelloService.java  
└── repository  
    └── HelloRepository.java  
```

---

### 2. 🧱 계층별 역할 (MVC)

| 계층         | 설명                                                   |
|--------------|--------------------------------------------------------|
| Controller   | 클라이언트 요청을 받아 Service에 전달하고 응답 반환       |
| Service      | 비즈니스 로직 처리 및 Repository 호출                   |
| Repository   | 데이터 반환 또는 DB와의 연동 (현재는 임시 문자열 반환)    |

---

### 3. ⚙️ DI (의존성 주입) 실습 요약

- `@Service`, `@Repository` 어노테이션으로 컴포넌트 등록
- 생성자 주입 방식 사용
- 각 클래스의 생성자에 `System.out.println()` 삽입하여 DI 작동 여부 확인

```markdown
HelloRepository 생성됨
HelloService 생성됨
HelloController 생성됨
```

---

### 4. 🏷️ 주요 어노테이션

| 어노테이션        | 설명                                               |
|-------------------|----------------------------------------------------|
| `@RestController` | REST API 응답을 반환하는 컨트롤러                  |
| `@Service`        | 서비스 계층 컴포넌트                                |
| `@Repository`     | DB 또는 데이터 접근 계층 컴포넌트                   |

---

### 5. 🎯 학습 효과

- 관심사 분리를 통해 **유지보수성 향상**
- 각 계층이 명확히 분리되어 **테스트 용이**
- 스프링 컨테이너가 객체를 **자동으로 생성 및 주입**하는 구조를 체험

---