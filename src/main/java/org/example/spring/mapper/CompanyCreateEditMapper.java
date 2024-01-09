package org.example.spring.mapper;

import org.example.spring.model.dto.CompanyCreateEditDto;
import org.example.spring.model.dto.CompanyReadDto;
import org.example.spring.model.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyCreateEditMapper implements Mapper<CompanyCreateEditDto, Company> {
    @Override
    public Company map(CompanyCreateEditDto object) {

        Company company = new Company();

        copy(object, company);

        return company;
    }

    @Override
    public Company map(CompanyCreateEditDto fromObject, Company toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    private void copy(CompanyCreateEditDto fromObject, Company toObject){
        toObject.setName(fromObject.getName());
    }


}
