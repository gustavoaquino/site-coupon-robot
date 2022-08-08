package br.com.cupom.service;


import br.com.cupom.domain.Company;
import br.com.cupom.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Optional<Company> findCompanyByName(String nameCompany){
        return companyRepository.findCompanyByUriSocialSoul(nameCompany);
    }

    public List<Company> findAllCompanies(){
        return this.companyRepository.findAll();
    }

    public List<Company> findAllComapaniesOrderByName(){
        return this.companyRepository.findAllCompanyByOrderByNameCompanyAsc();
    }

    public List<Company> findAllTop6OrderByIdAsc(){return this.companyRepository.findAllLimit6ByOrderByIdAsc();}


}
