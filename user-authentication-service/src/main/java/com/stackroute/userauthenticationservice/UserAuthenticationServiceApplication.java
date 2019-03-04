package com.stackroute.userauthenticationservice;

import com.stackroute.userauthenticationservice.model.User;
import com.stackroute.userauthenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
@SpringBootApplication
public class UserAuthenticationServiceApplication {
//	@Autowired
//	UserRepository userRepository;
//	private final static String QUEUE_NAME = "register";
//	String message;


	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		try {
//			ConnectionFactory factory = new ConnectionFactory();
//			factory.setHost("localhost");
//			Connection connection = factory.newConnection();
//			Channel channel = connection.createChannel();
//			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//				message = new String(delivery.getBody(), "UTF-8");
//				System.out.println(" [x] Received '" + message + "'");
//				String messages[] = message.split(",");
//				User user = new User(messages[0], messages[1]);
//				System.out.println(userRepository.save(user));
//			};
//			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
//			});
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
}

