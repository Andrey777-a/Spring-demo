package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.model.dto.UserReadDto;
import org.example.spring.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {

        var company = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);

        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getBirthdate(),
                object.getFirstname(),
                object.getLastname(),
                object.getRole(),
                company
        );
    }
}
