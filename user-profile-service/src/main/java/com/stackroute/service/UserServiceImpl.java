package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsd.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsd.rabbitmq.routingkey}")
    private String routingKey;

    public UserRepository userRepository;

    /*
    Constructor
     */

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    /*
    save userDTO in db
     */

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {

        if(userRepository.existsById(user.getEmail())){
            throw new UserAlreadyExistsException("userDTO already exists");
        }

        User savedUser = userRepository.save(user);
        produceMsg(savedUser);
        return savedUser;
    }

    //RabbitMq message producer method
    public void produceMsg(User msg){
        log.info("Sending message");
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }
}
