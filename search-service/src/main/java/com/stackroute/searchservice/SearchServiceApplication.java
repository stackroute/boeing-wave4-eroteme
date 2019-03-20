package com.stackroute.searchservice;

import com.stackroute.searchservice.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SearchServiceApplication {
    @Autowired
    SearchRepository searchRepository;


    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}











