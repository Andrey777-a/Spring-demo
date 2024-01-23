package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.model.dto.UserCreateEditDto;
import org.example.spring.model.entity.Company;
import org.example.spring.model.entity.User;
import org.example.spring.repository.CompanyRepository;
import org.example.spring.service.ImageService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

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


    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setBirthdate(object.getBirthdate());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));

        Optional.ofNullable(object.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);


        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> {
                    user.setImage(image.getOriginalFilename());

                    Path uploadLocation = Paths.get(imageService.getBucket());

                    Path filePath = uploadLocation.resolve(Objects.requireNonNull(image.getOriginalFilename()));

                    user.setImagePath(filePath.toString());

                });

    }


    private Company getCompany(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }

}
