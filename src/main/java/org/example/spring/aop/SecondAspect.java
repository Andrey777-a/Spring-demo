package org.example.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@Order(2)
public class SecondAspect {

    @Around("org.example.spring.aop.Pointcuts.anyFindByIdServiceMethod() " +
            "&& target(service) && args(id)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {

        var methodName = joinPoint.getSignature().getName();
        log.info("AROUND before - invoked {} method in class {}, with id {}",methodName, service, id);

        try {

            var start = System.nanoTime();
            Object result = joinPoint.proceed();
            var end = System.nanoTime();

            log.info("AROUND after returning - invoked {} method in class {}, result {}, running {} ms",
                    methodName, service, result, TimeUnit.NANOSECONDS.toMillis(end - start));

            return result;
        } catch (Throwable ex) {
            log.info("AROUND after throwing - invoked {} method in class {}, exception {}: {}",
                    methodName, service, ex.getClass(), ex.getMessage());
            throw ex;
        } finally {
            log.info("AROUND after (finally) - invoked {} method in class {}",methodName, service);
        }
    }

}
