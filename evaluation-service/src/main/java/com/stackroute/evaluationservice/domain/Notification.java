package com.stackroute.evaluationservice.domain;

import lombok.Data;

import java.util.List;

@Data
public class Notification {

    private List<String> emails;
    private String question;
}
