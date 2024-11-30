## 싱글톤 컨테이너 원리 이해하기

## 싱글톤 패턴 실습 정리

## 1. 싱글톤 패턴 개요

> 싱글톤 패턴(Singleton Pattern)은 애플리케이션 전체에서 하나의 인스턴스만 생성되도록 보장하는 디자인 패턴입니다.
>
>
> 주로 **공유 자원**, **설정 정보**, **로그 처리기**, **DB 연결 객체** 등에 사용됩니다.
>

---

## 2. Java 코드 예제

### Singleton.java

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {
        System.out.println("Singleton 생성자 호출");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### SingletonTest.java

```java
public class SingletonTest {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        System.out.println("obj1: " + obj1);
        System.out.println("obj2: " + obj2);

        if (obj1 == obj2) {
            System.out.println("동일한 인스턴스입니다.");
        } else {
            System.out.println("다른 인스턴스입니다.");
        }
    }
}
```

---

## 3. 실행 결과 (콘솔)

```
Singleton 생성자 호출
obj1: Singleton@3b07d329
obj2: Singleton@3b07d329
동일한 인스턴스입니다.
```

> ✔️ Singleton 생성자가 한 번만 호출되며 두 객체가 동일한 주소를 참조하고 있음이 확인됩니다.
>

---

## 4. 싱글톤 패턴의 장단점

| 구분 | 설명 |
| --- | --- |
| 장점 | - 메모리 절약 (인스턴스 1개)- 전역 접근 가능- 동일 인스턴스를 통한 일관된 동작 |
| 단점 | - 테스트 어려움 (Mock 대체 불가)- 멀티스레드 환경에서 동기화 처리 필요- 의존성 주입(DI)과 충돌 가능성 |

---

## 5. 사용 예시

- Logger (로깅)
- Configuration Manager (설정)
- Thread Pool
- Database Connection Pool

---