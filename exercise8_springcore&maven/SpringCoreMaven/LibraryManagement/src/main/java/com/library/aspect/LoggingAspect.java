package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Exercise 8: @Before advice -- runs BEFORE the matched method executes.
    // Cannot stop the method from running or change its result.
    @Before("execution(* com.library.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[BEFORE] About to call: " + joinPoint.getSignature());
    }

    // Exercise 8: @After advice -- runs AFTER the matched method completes
    // (whether it succeeded or threw an exception).
    @After("execution(* com.library.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AFTER] Finished call: " + joinPoint.getSignature());
    }

    // Exercise 3: @Around advice -- wraps the method call, so we can measure
    // execution time by capturing timestamps before and after proceed().
    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // proceed() actually calls the real method (e.g. addBook, getAllBooks)
        Object result = joinPoint.proceed();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] " + joinPoint.getSignature() + " executed in " + elapsed + "ms");

        return result;
    }
}
