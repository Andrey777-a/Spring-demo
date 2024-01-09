package org.example.spring.model.dto;

import lombok.Value;
import org.example.spring.model.entity.Role;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long Id;
    String username;
    LocalDate birthdate;
    String firstname;
    String lastname;
    Role role;
    CompanyReadDto company;
}
