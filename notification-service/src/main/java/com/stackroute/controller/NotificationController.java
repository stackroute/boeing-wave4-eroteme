package com.stackroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;
  //notifies all the users sent by recommendation service regarding question which has been posted
    @Scheduled(fixedDelay = 6000)
    public void generateQuestionNotification() {
        String[] user = new String[]{"nan@gmail.com", "sita"};
        String Question = "What is Angular?";
        for (int i = 0; i < user.length; i++) {
            template.convertAndSend("/queue/" + user[i], "Can you answer this:" + Question);
        }
    }
    //notifies the user that your question has been answered
    @Scheduled(fixedDelay = 7000)
    public void generateAnswerNotification() {
        String user = "nan@gmail.com";
        String Question = "What is Angular?";
        template.convertAndSend("/queue/" + user, "Your question:" + Question + "has been answered!");
    }
    //notifies the user that thier answer has been accepted
    @Scheduled(fixedDelay = 8000)
    public void generateLikedNotification() {
        String user = "nan@gmail.com";
        String Question = "What is Angular?";
        template.convertAndSend("/queue/" + user, "Your Answer to the  question:" + Question + "has been accepted!");
    }
}
