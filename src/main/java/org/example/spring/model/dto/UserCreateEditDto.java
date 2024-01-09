package org.example.spring.model.dto;

import lombok.Value;
import org.example.spring.model.entity.Role;

import java.time.LocalDate;

@Value
public class UserCreateEditDto{

    String username;
    LocalDate birthdate;
    String firstname;
    String lastname;
    Role role;
    Integer companyId;

}
