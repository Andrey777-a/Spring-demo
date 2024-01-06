package org.example.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.integration.repository.annotation.IT;
import org.example.spring.model.entity.Role;
import org.example.spring.model.entity.User;
import org.example.spring.repository.CompanyRepository;
import org.example.spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class IntegrationMainDbTest {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Commit
    @Test
    void checkCreatedAuditUsers() {

        var company = companyRepository.findById(1).get();

        var user = User.builder()
                .username("Test10@Gmail.com")
                .firstname("Ivan")
                .lastname("Sidorov")
                .birthdate(LocalDate.of(2001, 1, 30))
                .company(company)
                .role(Role.ADMIN)
                .build();

        userRepository.saveAndFlush(user);

        var byId = userRepository.findById(15L);
        assertTrue(byId.isPresent());

        assertNotNull(byId.get().getCreatedAt());

    }

}
