package com.rest.springbootemployee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company update(Integer id, Company company) {
        Company existingCompany = companyRepository.findById(id);
        if (company.getName() != null) {
            existingCompany.setName(company.getName());
        }
        return existingCompany;
    }


    public Company findById(Integer id) {
        return companyRepository.findById(id);
    }

    public List<Employee> getEmployees(int companyId) {
        return companyRepository.getEmployees(companyId);
    }

    public List<Company> findByPage(Integer page, Integer pageSize){
        return companyRepository.findByPage(page, pageSize);
    }

    public Company create(Company company) {
        return companyRepository.create(company);
    }

    public void delete(int companyId) {
        companyRepository.delete(companyId);
    }
}
