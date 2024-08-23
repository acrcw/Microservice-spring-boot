package com.joban.companymicroservice.company.Service;

import com.joban.companymicroservice.company.dto.JobMessageDto;
import com.joban.companymicroservice.company.dto.ReviewMessageDto;
import com.joban.companymicroservice.company.model.Company;
import com.joban.companymicroservice.company.dto.CompanyDto;

import java.util.List;

public interface CompanyService { // created a interface to promote lose coupling
    List<Company> getAllCompanies();
    Company createCompany(CompanyDto j);
    List<Company> getCompanyBySearch(String term);
    Company updateCompany(String id, CompanyDto j);
//    Company updateJobDetails(String id);
    Company deleteCompany(String id);
    Company getCompanyById(String id);
    void updateCompanyRating(ReviewMessageDto msg);
    void updateCompanyJobCount(JobMessageDto msg);
}
