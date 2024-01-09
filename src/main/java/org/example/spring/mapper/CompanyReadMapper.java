package org.example.spring.mapper;

import org.example.spring.model.dto.CompanyReadDto;
import org.example.spring.model.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(object.getId(), object.getName());
    }

    @Override
    public CompanyReadDto map(Company fromObject, CompanyReadDto toObject) {
        return toObject;
    }
}
