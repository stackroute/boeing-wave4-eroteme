package com.stackroute;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.stackroute.domain.Question;
import com.stackroute.domain.User;
import com.stackroute.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class QuestionanswerserviceApplication{
	@Autowired
	QuestionRepository questionRepository;

	public final static String QUEUE_Name = "question-answer-message-queue";
	public final static String EXCHANGE_Name = "spring-boot-exchange";
	public final static String ROUTING_KEY = "routing-key";

	@Bean
	Queue queue() {
		return new Queue(QUEUE_Name, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_Name);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	public static void main(String[] args) {
		SpringApplication.run(QuestionanswerserviceApplication.class, args);
	}
}
