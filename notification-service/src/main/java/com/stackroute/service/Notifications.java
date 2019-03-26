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
public class Notifications implements NotificationsInterface {

    private SimpMessagingTemplate template;
    private NotificationRepository notificationRepository;
    private com.stackroute.domain.Notifications notifications=new com.stackroute.domain.Notifications();
    private List<String> notify=new ArrayList<String>();
    private String note="";

    @Autowired
    public Notifications(SimpMessagingTemplate template, NotificationRepository notificationRepository) {
        this.template = template;
        this.notificationRepository = notificationRepository;
    }

//method to generate notifications when the user is logged in
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
        //delete those mails once sent to the client
        notificationRepository.delete(email);
        log.info("deleted "+email);
        }
        catch(Exception e){
            System.out.println("No Notications for the user:"+email);
        }
    }

}
