package com.joban.jobmicroservice.clients;


import com.joban.jobmicroservice.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
// openfiegn uses ribbon under the hood
@FeignClient(name="COMPANYMICROSERVICE") // microservice name from eureka server
public interface CompanyClient {
//    rst.exchange("http://JOBMICROSERVICE:8082/api/v1/company/" + c.getId() + "/jobs",
//    HttpMethod.GET, // request method
//            null,  // request body if any
//            new ParameterizedTypeReference<List<Job>>() {} // response needs to be in this format
    @GetMapping("/api/v1/company/{cid}")
    Company getCompany(@PathVariable("cid") String cid);
}
