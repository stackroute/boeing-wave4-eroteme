package com.stackroute.nlp;

import com.stackroute.nlp.service.NlpServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
	public class NlpApplication {

		public static void main(String[] args) {
			SpringApplication.run(NlpApplication.class, args);
			NlpServiceImpl nlpService = new NlpServiceImpl();
			nlpService.setquestion("What is angular in java");
			nlpService.showAllResults();
		}
	}
