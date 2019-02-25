package com.stackroute;

import com.stackroute.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@SpringBootApplication
public class QuestionanswerserviceApplication {
	@Autowired
	QuestionRepository questionRepository;

	public final static String QUEUE_Name = "question-answer-message-queue";

	@Bean
	Queue queue() {
		return new Queue(QUEUE_Name, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(QUEUE_Name);
	}
	public static void main(String[] args) {
		SpringApplication.run(QuestionanswerserviceApplication.class, args);
	}
}
