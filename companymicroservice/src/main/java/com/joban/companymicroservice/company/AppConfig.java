package com.joban.companymicroservice.company;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig { // with openfiegn this class is not needed || only needed with rest template with microservice names
    @Bean // object managed by spring framework
    @LoadBalanced //configures rest template to use load balance client under the hood to make api calls with service names
    // ribbon is used under the hood for loadbalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
