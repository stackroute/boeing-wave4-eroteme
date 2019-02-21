package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/v1")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    PasswordEncoder encoder;

    /*
    saving user in db
     */
    private final static String QUEUE_NAME = "register";
    @PostMapping("signup")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException,Exception {
        User signUp = new User(user.getEmail(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getInterests());
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = signUp.getEmail()+','+encoder.encode(signUp.getPassword());
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        try{
            //User signUp1 = new User(user.getFirstName(),user.getLastName(),user.getEmail(),encoder.encode(user.getPassword()),user.getInterests());
            userService.saveUser(signUp);
            return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
        }

    }
}

