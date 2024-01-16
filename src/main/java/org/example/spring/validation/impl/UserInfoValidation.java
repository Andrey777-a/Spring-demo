package org.example.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.spring.model.dto.UserCreateEditDto;
import org.example.spring.validation.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserInfoValidation implements ConstraintValidator<UserInfo, UserCreateEditDto> {

    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) || StringUtils.hasText(value.getLastname());
    }
}
