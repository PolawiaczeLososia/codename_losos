package com.erpsystem.entity.company;

import com.erpsystem.entity.company.company.Company;
import com.erpsystem.entity.company.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repositoryCompany;

    @Override
    public Company save(Company company) {
        return repositoryCompany.save(company);
    }

    @Override
    public Company deleteById(Integer id) {
        Company company = repositoryCompany.findById(id).orElse(null);
        if(company != null)
            repositoryCompany.deleteById(id);
        return company;
    }

    @Override
    public Company overwrite(Company company) {
        if(repositoryCompany.findById(company.getId()).orElse(null)==null)
            return null;
        else
            return repositoryCompany.save(company);
    }

    @Override
    public List<Company> findAll() {

        List<Company> companies = new ArrayList<>();
        repositoryCompany.findAll().forEach(companies::add);

        return companies;
    }

    @Override
    public Company findById(Integer id) {
        return repositoryCompany.findById(id).orElse(null);
    }

}
