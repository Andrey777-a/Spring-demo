package org.example.spring.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.spring.validation.impl.UserInfoValidation;

import java.lang.annotation.*;

@Constraint(validatedBy = UserInfoValidation.class)
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface UserInfo {

    String message() default "Lastname and firstname must not be empty";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
