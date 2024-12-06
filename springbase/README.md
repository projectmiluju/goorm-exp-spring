## **순환 의존성 문제 해결 예제**

## 1단계: 순환 의존성 발생 구조 만들기

### 📁 프로젝트 구조

```
src/
 └── main/
     └── java/com/example/springBase/
		     ├── A.java
		     ├── B.java
         ├── controller/GreetingCotroller.java
         ├── service/
			   │   ├── EnglishGreetingService.java
			   │   ├── KoreanGreetingService.java
         │   └── GreetingService.java
         ├── AppRunner.java
         └── SpringBaseApplication.java
```

### 📄 `A.java`

```java
package com.example.springBase;

import org.springframework.stereotype.Component;

@Component
public class A {

    private final B b;

    public A(B b) {
        this.b = b;
    }

    public void hello() {
        System.out.println("A가 호출됨");
    }
}

```

---

### 📄 `B.java`

```java
package com.example.circulardemo;

import org.springframework.stereotype.Component;

@Component
public class B {

    private final A a;

    public B(A a) {
        this.a = a;
    }

    public void hello() {
        System.out.println("B가 호출됨");
    }
}

```

---

### 📄 `AppRunner.java`

```java
package com.example.springBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private A a;

    @Override
    public void run(String... args) throws Exception {
        a.hello();
    }
}
```

---

### 📄 `SpringBaseApplication.java`

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

🧨 실행 결과 (의도된 에러 발생)

<img src="https://github.com/projectmiluju/goorm-exp-spring/blob/feature/circulardependency/springbase/src/main/asset/1.png" width="50%" height="50%" alt="순환 의존성 에러">

## 2단계: 순환 의존성 해결 (`@Lazy` 사용)

### 📄 `A.java` 수정

```java
@Component
public class A {

    private final B b;

    public A(@Lazy B b) {
        this.b = b;
    }

    public void hello() {
        System.out.println("A가 호출됨");
        b.hello(); // b도 호출 확인
    }
}

```

---

### 📄 `B.java` 그대로 사용

---

### ✅ 실행 결과

```
A가 호출됨
B가 호출됨
```

> 💡 순환 의존이 생성자 주입으로 발생할 경우, @Lazy를 사용하여 지연 주입하면 해결할 수 있습니다.
>