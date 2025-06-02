package com.example.springBase.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 메서드 실행 전
    @Before("execution(* com.example.springBase.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[Before] 메서드 실행 전: " + joinPoint.getSignature().getName());
    }

    // 메서드 실행 후
    @After("execution(* com.example.springBase.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[After] 메서드 실행 후: " + joinPoint.getSignature().getName());
    }

    // 반환값 확인
    @AfterReturning(pointcut = "execution(* com.example.springBase.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AfterReturning] 반환값: " + result);
    }
}