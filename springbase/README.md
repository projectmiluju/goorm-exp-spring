## **빈 스코프: Singleton vs Prototype**

### 📁 패키지 구조

```
src/main/java/com/example/springBase/
├── SpringBaseApplication.java
├── SingletonBean.java
├── PrototypeBean.java
└── AppRunner.java
```

## ✅ 코드 작성

### 📄 `SingletonBean.java`

```java
package com.example.springBase;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // 생략해도 기본은 singleton
public class SingletonBean {

    public SingletonBean() {
        System.out.println("SingletonBean 생성됨: " + this);
    }

    @PostConstruct
    public void init() {
        System.out.println("SingletonBean 초기화 메서드 호출됨");
    }
}

```

---

### 📄 `PrototypeBean.java`

```java
package com.example.springBase;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {

    public PrototypeBean() {
        System.out.println("PrototypeBean 생성됨: " + this);
    }

    @PostConstruct
    public void init() {
        System.out.println("PrototypeBean 초기화 메서드 호출됨");
    }
}

```

---

### 📄 `AppRunner.java`

```java
package com.example.springBase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final ApplicationContext context;

    public AppRunner(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {
        System.out.println("==== SingletonBean 테스트 ====");
        SingletonBean singleton1 = context.getBean(SingletonBean.class);
        SingletonBean singleton2 = context.getBean(SingletonBean.class);
        System.out.println("singleton1 == singleton2: " + (singleton1 == singleton2));

        System.out.println("==== PrototypeBean 테스트 ====");
        PrototypeBean proto1 = context.getBean(PrototypeBean.class);
        PrototypeBean proto2 = context.getBean(PrototypeBean.class);
        System.out.println("proto1 == proto2: " + (proto1 == proto2));
    }
}

```

---

### 📄 `ScopeDemoApplication.java`

```java
package com.example.springBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScopeDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScopeDemoApplication.class, args);
    }
}

```

---

## ✅ 실행 결과 예시 (콘솔)

```
SingletonBean 생성됨: com.example.springBase.SingletonBean@33532d
SingletonBean 초기화 메서드 호출됨
==== SingletonBean 테스트 ====
singleton1 == singleton2: true

==== PrototypeBean 테스트 ====
PrototypeBean 생성됨: com.example.springBase.PrototypeBean@36f3a8c3
PrototypeBean 초기화 메서드 호출됨
PrototypeBean 생성됨: com.example.springBase.PrototypeBean@88a9c4f
PrototypeBean 초기화 메서드 호출됨
proto1 == proto2: false

```

---

## ✅ 정리

| 구분 | singleton | prototype |
| --- | --- | --- |
| 정의 | Spring 컨테이너에서 1개의 인스턴스 유지 | 요청할 때마다 새로운 인스턴스 생성 |
| 특징 | 공유 인스턴스 | 매번 다른 객체 |
| 사용 예시 | 대부분의 서비스 클래스 | 상태를 가지는 객체, 사용자 입력 기반 객체 |
| 테스트 결과 | 동일 객체 주소 반환 | 서로 다른 주소 반환 |

---

##