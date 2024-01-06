package org.example.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.IntegrationTestBase;
import org.example.spring.integration.repository.annotation.IT;
import org.example.spring.model.entity.Company;
import org.example.spring.repository.CompanyRepository;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class CompanyRepositoryTest extends IntegrationTestBase {


    private final CompanyRepository companyRepository;

    @Test
    void checkAuditCompany(){
        var company = Company.builder().name("test5")
                .companyLocales(Map.of(
                        "ru", "test описание",
                        "en", "test description"
                ))
                .build();

        companyRepository.save(company);
    }
    @Test
    void checkCompanyId() {
        var byId = companyRepository.findById(1);
        assertTrue(byId.isPresent());

    }

    @Test
    void checkCompanyName() {
        var byId = companyRepository.findByName("Google");
        assertTrue(byId.isPresent());

    }

}