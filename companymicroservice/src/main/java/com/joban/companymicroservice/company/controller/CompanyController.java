package com.joban.companymicroservice.company.controller;

import com.joban.companymicroservice.company.Service.CompanyService;
import com.joban.companymicroservice.company.model.Company;
import com.joban.companymicroservice.company.dto.CompanyDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {


    CompanyService svc;

    // below constrcutor will only work if have declared service with @Service annotation
    public CompanyController(CompanyService svc) {
        this.svc = svc; // since i have marked the service class with @Service spring boot will auto create the instance during creation and inject it
        // i just need to create a constructor in controller
    }




    // to get all the companies ✅✅
    @GetMapping("/company")
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> cmp = svc.getAllCompanies();
        if(cmp!=null)
        {
            return new ResponseEntity<List<Company>>(cmp,HttpStatus.OK);
        }
        return new ResponseEntity<List<Company>>(HttpStatus.SERVICE_UNAVAILABLE);

    }

    // to create a company ✅✅
    @PostMapping("/company")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody CompanyDto j){
        Company rs = svc.createCompany(j);
        if(rs!=null)
        {
            return new ResponseEntity<Company>(rs,HttpStatus.CREATED);
        }
        return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
    }

    // to search for a job ✅
    @GetMapping("/company/search/{term}")
    public ResponseEntity<List<Company>> getCompanyBySearch(@PathVariable String term)
    {
        System.out.println("serached term >> " + term);
        List<Company> jList =svc.getCompanyBySearch(term);
        if(jList!=null)
        {
            return new ResponseEntity<List<Company>>(jList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);
    }
    // to update the created company fully ✅✅
    @PutMapping("/company/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable String id, @RequestBody CompanyDto j )
    {
        Company rv=svc.updateCompany(id,j);
        if(rv!=null){
            return new ResponseEntity<>(rv,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    // to partially update the job post
//    @PatchMapping("/jobs/{id}")
//    public ResponseEntity<Job> updateJobDetails(@PathVariable String id)
//    {
////       return svc.updateJobDetails(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    // to delete a company ✅✅
    @DeleteMapping("/company/{id}")
    public ResponseEntity<Company> deleteCompanyById(@PathVariable String id)
    {
        Company j =svc.deleteCompany(id);
        if(j!=null)
        {
            return new ResponseEntity<>(j,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // to get a company by id ✅✅
    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String id)
    {
        Company j =svc.getCompanyById(id);
        if(j!=null)
        {
            return new ResponseEntity<>(j,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
