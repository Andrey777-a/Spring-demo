package org.example.spring.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.annotation.IT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class CompanyRepositoryTest {

    @Autowired
    private final CompanyRepository companyRepository;
    /*@Autowired
    public CompanyRepositoryTest(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }*/

    @Test
    void checkCompanyId() {
        var byId = companyRepository.findById(1);
        assertTrue(byId.isPresent());

    }

    @Test
    void checkCompanyName() {
        var byId = companyRepository.findByName("google");
        assertTrue(byId.isPresent());

    }

}