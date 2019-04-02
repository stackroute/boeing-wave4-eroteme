package com.stackroute.nlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class NlpApplication {
	public static void main(String[] args) {
		SpringApplication.run(NlpApplication.class, args);

	}

}
