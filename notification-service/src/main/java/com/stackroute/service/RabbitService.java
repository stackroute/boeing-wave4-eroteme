package com.stackroute.service;

import com.stackroute.domain.NotificationDTO;
import com.stackroute.domain.Notifications;
import com.stackroute.domain.QuestionDTO;
import com.stackroute.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class RabbitService {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    NotificationRepository notificationRepository;

    Notifications notification=new Notifications();
    List<String> emailList=new ArrayList<>();
    List<String> notify=new ArrayList<>();

    //Rabbit Listener listening to Question and answer service
    @RabbitListener(queues = "${jsd.rabbitmq.queue}")
    public void receivedMessage(QuestionDTO msg) {

        log.info("received"+msg);

        if(msg.getAction()==Actions.QUESTION_ANSWER){
            String send="Your question:" + msg.getQuestion() + "has been answered!";
            template.convertAndSend("/queue/" +msg.getUser().getEmail(),send);
            try {
                Notifications note = notificationRepository.findById(msg.getUser().getEmail());
                notify = note.getNotification();
                notify.add("Your question:" + msg.getQuestion() + "has been answered!");
                note.setNotification(notify);
                notificationRepository.update(note);
                log.info("notification updated");
            }
            catch(Exception e) {
                notify.add("Your question:" + msg.getQuestion() + "has been answered!");
                notification.setEmail(msg.getUser().getEmail());
                notification.setNotification(notify);
                log.info("saving" + notification);
                notificationRepository.save(notification);
                log.info("notification saved");
            }
        }

        if(msg.getAction()==Actions.ANSWER_ACCEPT){
            log.info("received"+msg);
            String send="Your Answer to the  question:" + msg.getQuestion() + "has been accepted!";
            template.convertAndSend("/queue/" +msg.getAnswer().get(0).getUser().getEmail(),send);
            try {
                Notifications note = notificationRepository.findById(msg.getAnswer().get(0).getUser().getEmail());
                notify = note.getNotification();
                notify.add("Your Answer to the  question:" + msg.getQuestion() + "has been accepted!");
                note.setNotification(notify);
                notificationRepository.update(note);
                log.info("notification updated");
            }
            catch(Exception e){
                notify.add("Your Answer to the  question:" + msg.getQuestion() + "has been accepted!");
                notification.setEmail(msg.getAnswer().get(0).getUser().getEmail());
                notification.setNotification(notify);
                notificationRepository.save(notification);
                log.info("notification saved");
            }
        }
    }

    //Rabbit Listener listening to Recommendation Query Service
    @RabbitListener(queues = "${jst.rabbitmq.queue}")
    public void receivedMessage1(NotificationDTO msg) {
        log.info("received"+msg);
        emailList=msg.getEmails();
        for(int i=0;i<emailList.size();i++){
            template.convertAndSend("/queue/" +emailList.get(i),"Can you answer this:" + msg.getQuestion());
            try {
                Notifications note = notificationRepository.findById(emailList.get(i));
                notify = note.getNotification();
                notify.add("Can you answer this:" + msg.getQuestion());
                note.setNotification(notify);
                notificationRepository.update(note);
                log.info("notification updated");
            }
            catch(Exception e){
                notify.add("Can you answer this:" + msg.getQuestion());
                notification.setEmail(emailList.get(i));
                notification.setNotification(notify);
                notificationRepository.save(notification);
                log.info("notification saved");
            }
        }
    }

}
