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

## 트랜잭션 관리 및 롤백 실습

Spring의 `@Transactional`을 사용하여 트랜잭션을 관리하고, 예외 발생 시 자동으로 롤백되는 동작을 실습합니다.

### 1. 프로젝트 구조

```markdown
com.example.practice
├── controller
│   └── PostController.java
├── service
│   └── PostService.java ← 트랜잭션 관리 위치
├── repository
│   └── PostRepository.java
└── domain
    └── Post.java
```

---

### 2. 사용 기술 및 어노테이션

| 구성 요소       | 주요 어노테이션                                             |
|-------------|------------------------------------------------------|
| Entity      | @Entity, @Id, @GeneratedValue                        |
| Repository	 | JpaRepository, @Repository                           |
| Controller  | @RestController, @RequestMapping, @PostMapping, etc. |
| Service     | @Service, @Transactional                                   |
| DB 설정       | H2 Database, application.properties                  |

---

### 3. 트랜잭션 적용: PostService

```java
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post createPost(Post post) {
        postRepository.save(post);

        if (post.getTitle().contains("fail")) {
            throw new RuntimeException("강제 예외 발생: 트랜잭션 롤백 테스트");
        }

        return post;
    }
}
```

### 4. 테스트 시나리오

```http request
POST /posts
{
  "title": "정상 게시글",
  "content": "트랜잭션 테스트"
}
```

- 저장됨
- DB 반영 확인 가능

---

### 5. 학습 포인트 요약

| 개념               | 설명                                        |
| ---------------- | ----------------------------------------- |
| `@Transactional` | 메서드 내 DB 작업을 하나의 트랜잭션으로 처리, 예외 발생 시 전체 롤백 |
| 롤백 조건            | 기본 설정은 `RuntimeException` 발생 시 롤백         |
| 트랜잭션 위치          | **서비스 계층**에 선언하는 것이 일반적인 실무 구조            |


---