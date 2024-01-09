package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.mapper.CompanyCreateEditMapper;
import org.example.spring.mapper.CompanyReadMapper;
import org.example.spring.model.dto.CompanyCreateEditDto;
import org.example.spring.model.dto.CompanyReadDto;
import org.example.spring.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyReadMapper companyReadMapper;
    private final CompanyCreateEditMapper companyCreateEditMapper;

    @Transactional
    public CompanyReadDto create(CompanyCreateEditDto company) {
        return Optional.of(company)
                .map(companyCreateEditMapper::map)
                .map(companyRepository::save)
                .map(companyReadMapper::map)
                .orElseThrow();
    }


    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(companyReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id){

        return companyRepository.findById(id)
                .map(
                        entity -> {
                            companyRepository.delete(entity);
                            companyRepository.flush();
                            return true;
                        })
                .orElse(false);

    }


}
