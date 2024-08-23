package com.joban.reviewmicroservice.review;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean // object managed by spring framework
    @LoadBalanced //configures rest template to use load balance client under the hood to make api calls with service names
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
