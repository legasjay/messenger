package com.olegandreevich.messenger.aspects;

import com.olegandreevich.messenger.util.exceptions.UserNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.AfterReturning;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LoggingHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingHandlingAspect.class);

    /** * Логирование перед выполнением метода контроллера */
    @Before("execution(* com.example.controllers.*.*(..))")
    public void beforeControllerExecution(JoinPoint joinPoint) {
        logger.info("Executing controller method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    /** * Логирование после успешного выполнения метода контроллера */
    @AfterReturning(
            pointcut = "execution(* com.example.controllers.*.*(..))",
            returning = "result"
    )
    public void afterControllerExecution(JoinPoint joinPoint, Object result) {
        logger.info("Controller method executed successfully: {} with result: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    /** * Обработка исключений */
    @Around("execution(* com.example.controllers.*.*(..))")
    public Object handleExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            if (ex instanceof UserNotFoundException) {
                logger.error("User not found exception occurred", ex);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            }
            throw ex;
        }
    }
}
