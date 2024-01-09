package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.model.dto.UserCreateEditDto;
import org.example.spring.model.entity.Company;
import org.example.spring.model.entity.User;
import org.example.spring.repository.CompanyRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;
    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }


    private void copy(UserCreateEditDto object, User user){
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setBirthdate(object.getBirthdate());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));
    }


    private Company getCompany(Integer id){
        return Optional.ofNullable(id)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }

}
