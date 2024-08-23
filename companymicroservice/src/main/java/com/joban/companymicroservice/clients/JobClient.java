package com.joban.companymicroservice.clients;

import com.joban.companymicroservice.external.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
// openfiegn uses ribbon under the hood
@FeignClient(name="JOBMICROSERVICE") // microservice name from eureka server
public interface JobClient {
//    rst.exchange("http://JOBMICROSERVICE:8082/api/v1/company/" + c.getId() + "/jobs",
//    HttpMethod.GET, // request method
//            null,  // request body if any
//            new ParameterizedTypeReference<List<Job>>() {} // response needs to be in this format
    @GetMapping("/api/v1/jobs")
    List<Job> getJobs(@RequestParam("company") String cid);
    @GetMapping("/api/v1/jobs/count")
    int getJobsCountByCompanyId(@RequestParam("company") String cid);
}
