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
//	public static List<Question> readAllDataAtOnce(String file)
//	{
//		List<Question> questionList = new ArrayList<Question>();
//		try {
//			// Create an object of file reader
//			// class with CSV file as a parameter.
//			FileReader filereader = new FileReader(file);
//
//			// create csvReader object and skip first Line
//			CSVReader csvReader = new CSVReaderBuilder(filereader)
//					.build();
//			List<String[]> allData = csvReader.readAll();
//			// print Data
//			for (String[] row : allData) {
//				questionList.add(createQuestion(row));
//			}
//			return questionList;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return questionList;
//	}
//	    private static Question createQuestion(String row[]) throws NullPointerException {
//			String question = row[0];
//			List<String> topics;
//			if (row[1].contains("$")) {
//				String[] topic = row[1].split("$");
//				topics = Arrays.asList(topic);
//			} else {
//				topics = new ArrayList<>();
//				topics.add(row[1]);
//			}
//			long timestamp = Long.parseLong(row[2]);
//			User user = new User(row[3], row[4], row[5]);
//        // create and return question of this metadata
//        return new Question(0, question, null, topics, 0, timestamp, 0, user, null, null);
//	}
//
//    @Override
//    public void run(String... args) throws Exception {
//        int count = 0;
//        List<Question> questions = readAllDataAtOnce("Questions.csv");
//        for (Question q : questions) {
//            q.setQuestionId(++count);
//            System.out.println(questionRepository.save(q));
//        }
//        System.out.println(questionRepository.findAll().size());
//	}
}
