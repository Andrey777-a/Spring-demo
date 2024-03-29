package org.example.spring.model.dto;

import jakarta.validation.constraints.*;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.spring.model.entity.Role;
import org.example.spring.validation.UserInfo;
import org.example.spring.validation.group.CreateAction;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@UserInfo
@Value
public class UserCreateEditDto {

    @Email
    String username;
    @NotBlank(groups = CreateAction.class)
    String password;

    LocalDate birthdate;

//    @NotBlank
    @Size(min = 1, max = 33)
    String firstname;

//    @NotEmpty
    String lastname;
    Role role;
    Integer companyId;
    MultipartFile image;
}
