package com.stackroute.evaluationservice.domain;

import lombok.Data;

import java.util.List;

@Data
public class Notification {

    //Tell Notification Team about this model

    List<String> emails;
    String question;
}
