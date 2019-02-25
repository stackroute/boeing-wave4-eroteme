package com.stackroute;

import com.stackroute.controller.NotificationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication implements CommandLineRunner {

    @Autowired
    NotificationController notificationController;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        notificationController.generateQuestionNotification();
        notificationController.generateAnswerNotification();
        notificationController.generateLikedNotification();
    }

}
