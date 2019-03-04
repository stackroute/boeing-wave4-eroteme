package com.stackroute.userauthenticationservice.security.service;

import com.stackroute.userauthenticationservice.model.User;
import com.stackroute.userauthenticationservice.model.UserDTO;
import com.stackroute.userauthenticationservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        UserRepository userRepository;

        @Override
        @Transactional
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(""));
            return new UserPrinciple(user.getEmail(),user.getPassword());
        }

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(UserDTO msg) {
        System.out.println("Received Message: " + msg);
        User us=new User();
        us.setEmail(msg.getEmail());
        us.setPassword(msg.getPassword());
        userRepository.save(us);
    }
}

