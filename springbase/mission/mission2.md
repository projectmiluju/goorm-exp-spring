## Spring을 통한 객체 지향 원리 적용 실습

---

### **다형성을 활용한 결제 서비스 구현 예제**

---

### **1. 개요**

**다형성**을 활용하여 다양한 결제 방식을 처리하는 방법을 설명합니다. **CreditCardPayment**, **PayPalPayment**, **BankTransferPayment** 등 여러 결제 방식에 대해 **PaymentService**라는 공통 인터페이스를 사용하여 결제를 동적으로 처리하는 방법을 구현합니다. 스프링의 **의존성 주입**(DI)과 **다형성**을 통해 결제 서비스를 확장 가능합니다.

---

### **2. 결제 서비스 구현**

### **2.1. PaymentService 인터페이스 정의**

먼저, 결제 방식에 공통된 메서드를 정의한 `PaymentService` 인터페이스를 생성합니다.

```java
public interface PaymentService {
    String processPayment(double amount);
}
```

### **2.2. 다양한 결제 방식 구현**

`PaymentService` 인터페이스를 구현하여 각각의 결제 방식을 구현합니다.

### **CreditCardPayment 클래스**

```java
import org.springframework.stereotype.Service;

@Service("creditCardPayment")
public class CreditCardPayment implements PaymentService {
    @Override
    public String processPayment(double amount) {
        return "Credit Card Payment of $" + amount + " processed successfully!";
    }
}
```

### **PayPalPayment 클래스**

```java
import org.springframework.stereotype.Service;

@Service("payPalPayment")
public class PayPalPayment implements PaymentService {
    @Override
    public String processPayment(double amount) {
        return "PayPal Payment of $" + amount + " processed successfully!";
    }
}
```

### **BankTransferPayment 클래스**

```java
import org.springframework.stereotype.Service;

@Service("bankTransferPayment")
public class BankTransferPayment implements PaymentService {
    @Override
    public String processPayment(double amount) {
        return "Bank Transfer Payment of $" + amount + " processed successfully!";
    }
}
```

### **2.3. PaymentServiceFactory 클래스**

`PaymentServiceFactory` 클래스를 만들어 사용자가 원하는 결제 방식을 선택하고, 해당 결제 방식 객체를 반환합니다. 이 클래스는 다형성을 활용하여 다양한 결제 방식 객체를 관리하고 제공합니다.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFactory {

    private final PaymentService creditCardPayment;
    private final PaymentService payPalPayment;
    private final PaymentService bankTransferPayment;

    @Autowired
    public PaymentServiceFactory(PaymentService creditCardPayment, PaymentService payPalPayment, PaymentService bankTransferPayment) {
        this.creditCardPayment = creditCardPayment;
        this.payPalPayment = payPalPayment;
        this.bankTransferPayment = bankTransferPayment;
    }

    public PaymentService getPaymentService(String paymentMethod) {
        switch (paymentMethod) {
            case "creditCard":
                return creditCardPayment;
            case "payPal":
                return payPalPayment;
            case "bankTransfer":
                return bankTransferPayment;
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }
    }
}
```

### **2.4. PaymentServiceController 클래스**

REST API를 통해 결제 방식을 선택하고, 결제 처리를 할 수 있도록 **Controller**를 작성합니다.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentServiceController {

    private final PaymentServiceFactory paymentServiceFactory;

    @Autowired
    public PaymentServiceController(PaymentServiceFactory paymentServiceFactory) {
        this.paymentServiceFactory = paymentServiceFactory;
    }

    @GetMapping("/processPayment/{method}/{amount}")
    public String processPayment(@PathVariable String method, @PathVariable double amount) {
        PaymentService paymentService = paymentServiceFactory.getPaymentService(method);
        return paymentService.processPayment(amount);
    }
}
```

---

### **3. 스프링 컨테이너에서의 다형성 지원**

스프링 컨테이너는 의존성 주입(Dependency Injection)을 통해 다양한 결제 방식 객체들을 관리하고 자동으로 주입합니다. 다형성을 활용하는 방법은 다음과 같습니다:

1. **`@Service` 어노테이션**: `PaymentService` 인터페이스를 구현한 `CreditCardPayment`, `PayPalPayment`, `BankTransferPayment` 클래스는 모두 `@Service` 어노테이션을 사용하여 스프링의 빈으로 등록됩니다. 이로써 스프링은 각 결제 방식 클래스를 관리하고, 필요한 곳에 자동으로 주입할 수 있습니다.
2. **`@Autowired` 어노테이션**: `PaymentServiceFactory`에서 각 결제 방식 구현체를 `@Autowired`를 통해 주입받습니다. 스프링은 `@Service` 어노테이션이 붙은 빈들을 찾아서 자동으로 주입합니다.
3. **다형성 적용**: `PaymentServiceFactory`는 사용자 요청에 맞는 결제 방식을 반환하는 팩토리 역할을 합니다. 클라이언트는 결제 방식을 선택하여 `processPayment()` 메서드를 호출하면, 선택된 결제 방식에 따라 다형성을 통해 동적으로 처리됩니다.

---

### **4. 스프링 애플리케이션 설정 및 실행**

### **Spring Boot 애플리케이션 클래스**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
```

---

### **5. 요청 예시**

RESTful API를 통해 결제를 처리할 수 있습니다.

- **Credit Card 결제**:

    ```
    GET /processPayment/creditCard/100
    응답: Credit Card Payment of $100 processed successfully!
    ```

- **PayPal 결제**:

    ```
    GET /processPayment/payPal/200
    응답: PayPal Payment of $200 processed successfully!
    ```

- **Bank Transfer 결제**:

    ```
    GET /processPayment/bankTransfer/300
    응답: Bank Transfer Payment of $300 processed successfully!
    ```


---

### **6. 다형성 활용 방법**

- **다형성의 원리**: `PaymentService` 인터페이스를 사용하여 여러 결제 방식 클래스를 다형성으로 처리합니다. `PaymentServiceFactory`는 사용자가 선택한 결제 방식에 맞는 구체적인 객체를 반환합니다.
- **유연성**: 새로운 결제 방식이 추가되더라도 `PaymentService` 인터페이스를 구현한 새로운 클래스를 추가하고, `PaymentServiceFactory`에서 해당 클래스를 관리하면 됩니다. 기존의 코드를 수정하지 않고도 새로운 결제 방식을 손쉽게 추가할 수 있습니다.

---

### **7. 결론**

**다형성**을 활용하여 결제 방식에 따라 동적으로 결제를 처리하는 시스템을 구현했습니다. 스프링의 의존성 주입(Dependency Injection)과 **다형성**을 결합하여, 각 결제 방식이 **PaymentService** 인터페이스를 통해 유연하게 처리될 수 있음을 확인할 수 있었습니다. 이 방법을 통해 **결제 방식**을 확장 가능하고, 유지보수 가능한 시스템으로 구축할 수 있습니다.