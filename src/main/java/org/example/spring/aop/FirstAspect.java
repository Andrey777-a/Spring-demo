package org.example.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
@Order(1)
public class FirstAspect {

    @Before(value = "org.example.spring.aop.Pointcuts.anyFindByIdServiceMethod() " +
            "&& args(id)" +
            "&& target(service)" +
            "&& this(serviceProxy)" +
            "&& @within(transactional)",
            argNames = "joinPoint,id,service,serviceProxy,transactional")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional){
        log.info("add logging before method " + joinPoint);
        log.info("add logging before method " + joinPoint.getThis().toString());
        log.info("add logging before method " + id);
        log.info("add logging before method " + service);
        log.info("add logging before method " + serviceProxy);
        log.info("add logging before method " + transactional);
    }

    @AfterReturning(value = "org.example.spring.aop.Pointcuts.anyFindByIdServiceMethod() " +
            "&& target(service)",
            returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("after returning - invoked findById method in class {}, result {}", service, result);
    }

    @AfterThrowing(value = "org.example.spring.aop.Pointcuts.anyFindByIdServiceMethod() " +
            "&& target(service)",
            throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("after throwing - invoked findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
    }

    @After("org.example.spring.aop.Pointcuts.anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("after (finally) - invoked findById method in class {}", service);
    }


}
