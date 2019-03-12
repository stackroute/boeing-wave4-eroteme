package com.stackroute.editprofile.controller;

import com.stackroute.editprofile.domain.UserCurrent;
import com.stackroute.editprofile.service.EditProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Slf4j
public class EditProfileController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //Recommendation Command Service
    @Value("${jfa.rabbitmq.exchange}")
    private String exchangeRCS;

    @Value("${jfa.rabbitmq.routingkey}")
    private String routingKeyRCS;

    //User-Profile service
    @Value("${jfb.rabbitmq.exchange}")
    private String exchangeUP;

    @Value("${jfb.rabbitmq.routingkey}")
    private String routingKeyUP;

    //Question and Answer Service
    @Value("${jsc.rabbitmq.exchange}")
    private String exchangeQA;

    @Value("${jsc.rabbitmq.routingkey}")
    private String routingKeyQA;

    //Login Service
    @Value("${jsd.rabbitmq.exchange}")
    private String exchangeLS;

    @Value("${jsd.rabbitmq.routingkey}")
    private String routingKeyLS;

    //Search Service
    @Value("${jse.rabbitmq.exchange}")
    private String exchangeSS;

    @Value("${jse.rabbitmq.routingkey}")
    private String routingKeySS;


    private EditProfileService editProfileService;

    @Autowired
    public EditProfileController(EditProfileService editProfileService) {
        this.editProfileService = editProfileService;
        this.amqpTemplate = amqpTemplate;
    }


    @PutMapping("updateDetails")
    public ResponseEntity<UserCurrent> saveUserDetails(@RequestBody UserCurrent userCurrent) {
        return new ResponseEntity<UserCurrent>(editProfileService.updateUserDetails(userCurrent), HttpStatus.OK);
    }


    //RabbitMq message producer method for Updated User Details
    public void produceEditedUser(UserCurrent userCurrent) {
        log.info("Sending message :" + userCurrent);
        amqpTemplate.convertAndSend(exchangeRCS, routingKeyRCS, userCurrent);
        amqpTemplate.convertAndSend(exchangeUP, routingKeyUP, userCurrent);
        amqpTemplate.convertAndSend(exchangeQA, routingKeyQA, userCurrent);
        amqpTemplate.convertAndSend(exchangeLS, routingKeyLS, userCurrent);
        amqpTemplate.convertAndSend(exchangeSS, routingKeySS, userCurrent);

    }


}
