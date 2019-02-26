package com.stackroute.nlp;

import com.stackroute.nlp.service.NlpServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
	public class NlpApplication {
	public final static String NLP_MESSAGE_QUEUE = "nlp-message-queue";
//	@Bean
//	Queue queue() {
//		return new Queue(NLP_MESSAGE_QUEUE, false);
//	}
//
//	@Bean
//	TopicExchange exchange() {
//		return new TopicExchange("spring-boot-exchange");
//	}
//
//	@Bean
//	Binding binding(Queue queue, TopicExchange exchange) {
//		return BindingBuilder.bind((Exchange) queue).to(exchange).with(NLP_MESSAGE_QUEUE);
//	}

		public static void main(String[] args) {
			SpringApplication.run(NlpApplication.class, args);
			NlpServiceImpl nlpService = new NlpServiceImpl();
			nlpService.setquestion("What is server Side in angular");
			nlpService.showAllResults();
		}

	}
