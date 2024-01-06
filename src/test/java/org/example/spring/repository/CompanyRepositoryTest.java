package org.example.spring.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.annotation.IT;
import org.example.spring.model.entity.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class CompanyRepositoryTest {


    private final CompanyRepository companyRepository;
    /*@Autowired
    public CompanyRepositoryTest(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }*/


    @Test
//    @Commit
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