package com.stackroute.searchservice;

import com.stackroute.searchservice.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchServiceApplication {
    @Autowired
    SearchRepository searchRepository;


    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}











