package org.example.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Pointcuts {
    /*
        @within - check annotation on the class level
     */

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer(){
    }

    /*
        within - check class type name
     */
    @Pointcut("within(org.example.spring.service.*Service)")
    public void isServiceLayer() {
    }
    /*
        this - check AOP proxy class type
        target - check target object class type
     */
//    @Pointcut("target(org.springframework.data.repository.Repository)")
    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void test(){
    }

    /*
        @annotation - check annotation on method level
     */

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping(){
    }

    /*
        args - check method param type
        * - any param type
        .. - 0+ any params type
     */
    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam(){
    }

    /*
        @args - check annotation on the param type
        * - any param type
        .. - 0+ any params type
     */
    @Pointcut("isControllerLayer() && @args(org.example.spring.validation.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {
    }

    /*
        bean - check bean name
     */
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    /*
        execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
     */
    @Pointcut("execution(public * org.example.spring.service.UserService.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Pointcut("execution(public * org.example.spring.service.UserService.*(*))")
    public void anyServiceMethod() {
    }






}
