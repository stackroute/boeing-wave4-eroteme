package com.stackroute.editprofile.service;

import com.stackroute.editprofile.domain.UserCurrent;
import com.stackroute.editprofile.repository.EditProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EditProfileServiceImpl implements EditProfileService {

    private EditProfileRepository editProfileRepository;
    private AmqpTemplate amqpTemplate;
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
    @Value("${jfc.rabbitmq.exchange}")
    private String exchangeQA;

    @Value("${jfc.rabbitmq.routingkey}")
    private String routingKeyQA;

    //Login Service
    @Value("${jfd.rabbitmq.exchange}")
    private String exchangeLS;

    @Value("${jfd.rabbitmq.routingkey}")
    private String routingKeyLS;

    //Search Service
    @Value("${jfe.rabbitmq.exchange}")
    private String exchangeSS;

    @Value("${jfe.rabbitmq.routingkey}")
    private String routingKeySS;

    @Autowired
    public EditProfileServiceImpl(EditProfileRepository editProfileRepository, AmqpTemplate amqpTemplate) {
        this.editProfileRepository = editProfileRepository;
        this.amqpTemplate = amqpTemplate;
    }


    @Override
    public UserCurrent updateUserDetails(UserCurrent user) {
        UserCurrent userCurrent = editProfileRepository.save(user);
        return userCurrent;
    }

    public void produceEditedUser(UserCurrent userCurrent) {
        log.info("Sending message :" + userCurrent);
        amqpTemplate.convertAndSend(exchangeRCS, routingKeyRCS, userCurrent);
        amqpTemplate.convertAndSend(exchangeUP, routingKeyUP, userCurrent);
        amqpTemplate.convertAndSend(exchangeQA, routingKeyQA, userCurrent);
        amqpTemplate.convertAndSend(exchangeLS, routingKeyLS, userCurrent);
        amqpTemplate.convertAndSend(exchangeSS, routingKeySS, userCurrent);
    }

}
