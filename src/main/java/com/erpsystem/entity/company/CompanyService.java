package com.erpsystem.entity.company;

import com.erpsystem.entity.company.company.Company;

import java.util.List;

public interface CompanyService {
    Company save(Company company);
    Company deleteById(Integer id);
    Company overwrite(Company company);

    List<Company> findAll();
    Company findById(Integer id);
}
