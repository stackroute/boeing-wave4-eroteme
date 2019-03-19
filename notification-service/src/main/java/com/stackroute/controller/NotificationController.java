package com.stackroute.controller;

import com.stackroute.repository.NotificationRepository;
import com.stackroute.service.Notifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1")
public class NotificationController {
    @Autowired
    private Notifications notifications;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    public NotificationController(Notifications notifications) {
        this.notifications = notifications;
    }

    //getting email of the user
    @GetMapping("/{email}")
    public ResponseEntity<String> sendNotification(@PathVariable String email) {
        log.info("user Logged in"+email);
        notifications.generateNotifications(email);
        return new ResponseEntity<String>("Notified Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all/now")
    public Map<String, com.stackroute.domain.Notifications> all() {
        return notificationRepository.findAll();
    }
}
