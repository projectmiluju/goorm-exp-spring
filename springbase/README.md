## **의존관계 자동 주입: `@Autowired`, `@Qualifier`, `@Primary`**

### 📁 프로젝트 구조

```
src/
 └── main/
     └── java/com/example/springBase/
         ├── controller/GreetingCotroller.java
         ├── service/
			   │   ├── EnglishGreetingService.java
			   │   ├── KoreanGreetingService.java
         │   └── GreetingService.java
         ├── AppRunner.java
         └── SpringBaseApplication.java
```

---

### 📄 GreetingService.java (인터페이스)

```java
package com.example.springBase.service;

public interface GreetingService {
    String greet();
}
```

---

### 📄 EnglishGreetingService.java (구현 클래스, 빈 등록)

```java
package com.example.springBase.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("englishGreetingService") // 이름 지정
public class EnglishGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Hello! (English)";
    }
}
```

---

### 📄 KoreanGreetingService.java (구현 클래스, 빈 등록)

```java
package com.example.springBase.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 기본 주입 대상
public class KoreanGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "안녕하세요! (Korean)";
    }
}

```

---

### 📄 AppRunner.java (빈 주입 및 실행)

```java
package com.example.springBase;

import com.example.springBase.controller.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private GreetingController controller;

    @Override
    public void run(String... args) throws Exception {
        controller.printGreetings();
    }
}

```

---

### 📄 SpringBaseApplication.java

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

### 주입 방식 비교표

| 구분 | 설명 | 사용 상황 |
| --- | --- | --- |
| `@Autowired` | 타입 기준 자동 주입 | 기본적인 자동 주입 |
| `@Qualifier` | 동일 타입의 여러 빈 중 이름 기준으로 지정 | 특정 구현체 명시 주입 필요할 때 사용 |
| `@Primary` | 기본으로 주입될 우선순위 빈 지정 | 여러 구현체 중 기본으로 사용할 구현체 설정 시 |