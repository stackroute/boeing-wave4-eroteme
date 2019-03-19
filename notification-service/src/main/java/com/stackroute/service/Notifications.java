package com.stackroute.service;

import com.stackroute.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
@Slf4j
public class Notifications {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private NotificationRepository notificationRepository;

    com.stackroute.domain.Notifications notifications=new com.stackroute.domain.Notifications();
    List<String> notify=new ArrayList<String>();
    String note="";

    public void generateNotifications(String email){
        //extract notification from repo for this email
        try{
        notifications=notificationRepository.findById(email);
        notify=notifications.getNotification();
        for(int i=0;i<notify.size();i++) {
            note=notify.get(i);
            template.convertAndSend("/queue/" +notifications.getEmail(),note);
            log.info("notified "+email);
        }
        notificationRepository.delete(email);
        log.info("deleted "+email);
        }
        catch(Exception e){
            System.out.println("exception in generate notifications ");
        }
    }

}
