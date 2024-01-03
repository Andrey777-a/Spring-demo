package org.example.spring.repository;

import org.example.spring.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("select c from Company c join fetch c.companyLocales where c.name like %:name%")
    Optional<Company> findByName(String name);

}
