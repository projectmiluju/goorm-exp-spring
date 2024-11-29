## 스프링 핵심 원리를 활용한 간단한 예제 만들기

---

### **DI (의존성 주입), IoC (제어의 역전), AOP (관점 지향 프로그래밍) 핵심 원리를 적용한 예제**

---

### **1. 예제 코드**

### **1.1. DI (의존성 주입) 및 IoC (제어의 역전) 구현**

이 예제에서는 **DI**와 **IoC**를 활용하여 `OrderService`가 `PaymentService`에 의존성을 주입받는 구조를 구현합니다.

### **PaymentService 인터페이스**

```java
public interface PaymentService {
    String processPayment(double amount);
}
```

### **CreditCardPayment 클래스 (PaymentService 구현)**

```java
import org.springframework.stereotype.Service;

@Service("creditCardPayment")
public class CreditCardPayment implements PaymentService {
    @Override
    public String processPayment(double amount) {
        return "Processed payment of $" + amount + " through Credit Card.";
    }
}
```

### **OrderService 클래스**

`OrderService`는 `PaymentService`에 의존하고 있으며, 의존성은 스프링 컨테이너에 의해 주입됩니다.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final PaymentService paymentService;

    // DI를 통한 의존성 주입
    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String createOrder(double amount) {
        return paymentService.processPayment(amount);
    }
}
```

### **1.2. AOP (관점 지향 프로그래밍) 구현**

AOP를 활용하여 `OrderService`의 결제 처리 로직에 부가적인 관심사(로그 기록)를 추가합니다.

### **AOP Aspect 클래스**

```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 주문 생성 전에 로그를 출력하는 Aspect
    @Before("execution(* OrderService.createOrder(..))")
    public void logBefore() {
        System.out.println("AOP - Before: A new order is being created.");
    }
}
```

### **2. 스프링 애플리케이션 설정 및 실행**

### **Spring Boot 애플리케이션 클래스**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### **컨트롤러 클래스 (테스트용)**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/createOrder/{amount}")
    public String createOrder(@PathVariable double amount) {
        return orderService.createOrder(amount);
    }
}
```

---

### **3. 원리 설명**

### **3.1. DI (의존성 주입)**

- 의존성 주입(DI)는 객체 간의 의존 관계를 외부에서 주입하는 방식입니다. **IoC 컨테이너**는 객체 생성과 의존 관계 설정을 담당하고, 객체의 생명주기와 상태를 관리합니다. `@Autowired` 어노테이션을 통해 **OrderService**는 필요한 **PaymentService**를 외부에서 주입받습니다.
- **OrderService**는 **PaymentService**에 의존하지만, **PaymentService**의 구현체는 **스프링 컨테이너**에서 주입됩니다.
- 이로써 **결합도가 낮고** 유연한 설계를 만들 수 있습니다.

### **3.2. IoC (제어의 역전)**

- 제어의 역전(IoC)은 객체의 생성과 흐름 제어를 **개발자**가 아닌 **프레임워크**가 담당하는 방식입니다. 스프링은 **IoC 컨테이너**를 통해 객체를 관리하고, 객체 간의 의존 관계를 주입합니다.
- **스프링 컨테이너**가 객체의 생명주기를 관리하고, 객체 간의 의존성을 설정합니다.
- `@Service` 어노테이션을 통해 **OrderService**와 **PaymentService** 객체는 스프링이 자동으로 생성하고 관리합니다.

### **3.3. AOP (관점 지향 프로그래밍)**

- AOP (관점 지향 프로그래밍)은 **핵심 비즈니스 로직**과 **부가적인 관심사**를 분리하는 방식입니다. `LoggingAspect` 클래스를 사용하여 **주문 생성 전 로그를 출력**하는 부가적인 관심사를 `OrderService`의 비즈니스 로직에서 분리할 수 있습니다.
- **LoggingAspect** 클래스는 **주문 생성**이라는 핵심 비즈니스 로직과 **로그 기록**이라는 부가적인 관심사를 분리하여 관리합니다.
- AOP를 통해 공통 기능을 모듈화하여 코드 중복을 줄이고, 유지보수를 용이하게 합니다.

---

### **4. 실행 결과**

- 애플리케이션을 실행하고 `http://localhost:8080/createOrder/100` URL을 통해 결제 처리가 정상적으로 실행됩니다.
- **AOP**를 통해 `createOrder`가 호출되기 전에 로그가 출력됩니다.

**예시 로그:**

```
AOP - Before: A new order is being created.
Processed payment of $100 through Credit Card.
```

---

### **5. 결론**

**DI**, **IoC**, **AOP**를 활용하여 **유연성**과 **확장성**을 갖춘 설계를 구현했습니다. **의존성 주입**을 통해 **객체 간 결합도를 낮추고**, **제어의 역전**을 통해 객체 생성을 **스프링 컨테이너**에 위임하여 **유연한 객체 관리**가 가능하도록 했습니다. 또한, **AOP**를 사용하여 **부가적인 관심사**인 로그 기록을 **핵심 비즈니스 로직**에서 분리함으로써 **코드의 유지보수성**을 향상시켰습니다.