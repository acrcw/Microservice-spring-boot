package com.joban.companymicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableFeignClients
@SpringBootApplication
public class CompanymicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanymicroserviceApplication.class, args);
	}

	// we need a loadbalanced instance of rest template to make calls to other microsservices using rest template
	//	@Bean
	//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	//		return builder.build();
	//	}

}
