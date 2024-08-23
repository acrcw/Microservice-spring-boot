package com.joban.companymicroservice.company.repository;

import com.joban.companymicroservice.company.model.Company;

import java.util.List;

interface CompanySearch {
    List<Company> searchByTerm(String term);
}
