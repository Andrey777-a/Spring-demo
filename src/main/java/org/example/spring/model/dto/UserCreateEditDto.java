package org.example.spring.model.dto;

import jakarta.validation.constraints.*;
import lombok.Value;
import org.example.spring.model.entity.Role;
import org.example.spring.validation.UserInfo;
import org.example.spring.validation.group.CreateAction;

import java.time.LocalDate;

@UserInfo(groups = CreateAction.class)
@Value
public class UserCreateEditDto {

    @Email
    String username;

    LocalDate birthdate;

//    @NotBlank
    @Size(min = 1, max = 33)
    String firstname;

//    @NotEmpty
    String lastname;
    Role role;
    Integer companyId;

}
