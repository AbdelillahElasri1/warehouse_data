package com.datawarehouse.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodLoggingAspect {

    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    @Before("execution(* com.datawarehouse.service.implementation.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("{}[LOGGING ASPECT] - Before method: {}{}", ANSI_CYAN, joinPoint.getSignature().toShortString(), ANSI_RESET);
        for (int i = 0; i < args.length; i++) {
            log.info("{}Param {}: {}{}", ANSI_YELLOW, i + 1, args[i], ANSI_RESET);
        }
    }

    @AfterThrowing(pointcut = "execution(* com.datawarehouse.service.implementation.*.*(..))", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().toShortString();
        log.error("\u001B[31m[ERROR]\u001B[0m \u001B[33mException in method:\u001B[0m {}", methodName);
        log.error("\u001B[31m[ERROR]\u001B[0m \u001B[33mException message:\u001B[0m {}", exception.getMessage());
    }

    @AfterReturning(pointcut = "execution(* com.datawarehouse.service.implementation.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("{}[LOGGING ASPECT] - After method: {}{}", ANSI_CYAN, methodName, ANSI_RESET);
        log.info("[LOGGING ASPECT] - Returned value: {}", result);
    }

}
