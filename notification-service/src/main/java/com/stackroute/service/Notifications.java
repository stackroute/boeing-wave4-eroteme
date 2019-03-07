package com.stackroute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Notifications {

    @Autowired
    private SimpMessagingTemplate template;
    final static String destination ="/queue/";

    //notifies all the users sent by recommendation service regarding question which has been posted
    public void generateQuestionNotification(String[] user,String Question) {
        for (int i = 0; i < user.length; i++) {
            template.convertAndSend(destination+ user[i], "Can you answer this:" + Question);
        }
    }

    //notifies the user that your question has been answered
    public void generateAnswerNotification(String user,String Question) {
        template.convertAndSend(destination + user, "Your question:" + Question + "has been answered!");
    }

    //notifies the user that thier answer has been accepted
    public void generateLikedNotification(String user,String Question) {
        template.convertAndSend(destination + user, "Your Answer to the  question:" + Question + "has been accepted!");
    }



}
