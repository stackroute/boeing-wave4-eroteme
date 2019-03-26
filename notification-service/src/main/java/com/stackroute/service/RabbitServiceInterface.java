package com.stackroute.service;

import com.stackroute.DTO.NotificationDTO;
import com.stackroute.DTO.QuestionDTO;

public interface RabbitServiceInterface {
     void receivedMessage(QuestionDTO msg);
     void receivedMessage1(NotificationDTO msg);
}
