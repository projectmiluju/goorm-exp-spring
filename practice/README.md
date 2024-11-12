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

## 🧪 JPA를 활용한 CRUD 기능 구현

Spring Data JPA와 H2 DB를 활용하여 Post 엔티티에 대한 CRUD 기능을 구현한 실습입니다.

### 1. 프로젝트 구조

```markdown
com.example.practice
├── controller
│   └── PostController.java
├── model
│   └── Post.java
└── repository
    └── PostRepository.java 
```

---

### 2. 사용 기술 및 어노테이션

| 구성 요소         | 주요 어노테이션                          |
|--------------|-----------------------------------|
| Entity   | @Entity, @Id, @GeneratedValue  |
| Repository	      | JpaRepository, @Repository        |
| Controller   | @RestController, @RequestMapping, @PostMapping, etc. |
| DB 설정   | H2 Database, application.properties |

---

### 3. Post 엔티티 예시

```java
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    // 생성자, getter/setter 생략
}
```

---

### 4. PostRepository

| 메서드          | HTTP 메서드             | 설명        |
| ------------ | -------------------- | --------- |
| `create()`   | POST `/posts`        | 게시글 생성    |
| `findAll()`  | GET `/posts`         | 전체 게시글 조회 |
| `findById()` | GET `/posts/{id}`    | 특정 게시글 조회 |
| `update()`   | PUT `/posts/{id}`    | 게시글 수정    |
| `delete()`   | DELETE `/posts/{id}` | 게시글 삭제    |


---

### 5.application.properties 설정 (H2 + JPA)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

➡ H2 콘솔 접속: http://localhost:8080/h2-console
➡ JDBC URL: jdbc:h2:mem:testdb

---

### 6. 테스트 예시
```http request
# 게시글 생성
POST /posts
{
  "title": "Hello",
  "content": "World"
}

# 전체 조회
GET /posts

# 개별 조회
GET /posts/1

# 수정
PUT /posts/1
{
  "title": "Updated Title",
  "content": "Updated Content"
}

# 삭제
DELETE /posts/1
```

---

### 7. 학습 포인트 요약

- Spring Data JPA를 통한 빠른 CRUD 구현 
- DB 없이도 테스트 가능한 인메모리 DB(H2) 사용법 익힘
- RESTful API 기본 흐름을 직접 구성해봄 
- Entity ↔ Repository ↔ Controller 구조 연습

---