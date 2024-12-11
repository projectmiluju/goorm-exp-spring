## 실습 목표

- 인터페이스 정의
- 인터페이스를 구현하는 클래스 2개 생성
- 의존성 주입을 통해 구현체 사용
- 실행 결과로 다형성 확인

## 프로젝트 구조

```
src/main/java/com/example/springBase/
├── SpringBaseApplication.java
├── PaymentService.java      (인터페이스)
├── CardPaymentService.java  (구현체1)
├── KakaoPayService.java     (구현체2)
├── PaymentProcessor.java    (사용자)
└── AppRunner.java
```

## 코드 작성

### 📄 `PaymentService.java` (인터페이스)

```java
package com.example.springBase.service;

public interface PaymentService {
    String pay(int amount);
}
```

---

### 📄 `CardPaymentService.java` (구현 클래스 1)

```java
package com.example.springBase.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // 기본 주입 대상
public class CardPaymentService implements PaymentService {

    @Override
    public String pay(int amount) {
        return "카드로 " + amount + "원 결제 완료";
    }
}
```

---

### 📄 `KakaoPayService.java` (구현 클래스 2)

```java
package com.example.springBase.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("kakaoPay")
public class KakaoPayService implements PaymentService {

    @Override
    public String pay(int amount) {
        return "카카오페이로 " + amount + "원 결제 완료";
    }
}
```

---

### 📄 `PaymentProcessor.java` (사용 클래스)

```java
package com.example.springBase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    private final PaymentService paymentService;

    @Autowired
    public PaymentProcessor(@Qualifier("kakaoPay") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void process(int amount) {
        String result = paymentService.pay(amount);
        System.out.println(result);
    }
}
```

---

### 📄 `AppRunner.java`

```java
package com.example.springBase;

import com.example.springBase.service.PaymentProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final PaymentProcessor processor;

    public AppRunner(PaymentProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void run(String... args) throws Exception {
        processor.process(10000);
    }
}
```

---

### 📄 `InterfaceDemoApplication.java`

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

## 실행 결과 (콘솔)

```
카카오페이로 10000원 결제 완료
```

> @Qualifier("kakaoPay")를 통해 인터페이스 기반으로 원하는 구현체를 선택해서 주입한 결과입니다.
>

---

## ✅ 핵심 요약

| 항목 | 설명 |
| --- | --- |
| 인터페이스 사용 이유 | 구현체 교체를 유연하게 하고, 테스트 대역(Mock) 주입 가능 |
| `@Primary` | 기본 구현체 지정 |
| `@Qualifier` | 특정 구현체를 명시적으로 주입 |
| 응집도/결합도 | 결합도 낮추고, 테스트 및 유지보수에 유리한 구조 제공 |

---

##