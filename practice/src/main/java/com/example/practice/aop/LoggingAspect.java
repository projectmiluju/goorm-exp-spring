package com.example.practice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.practice.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("▶️ [START] " + methodName);

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();  // 실제 메서드 실행
        long end = System.currentTimeMillis();

        System.out.println("✅ [END] " + methodName + " (" + (end - start) + " ms)");
        return result;
    }
}
