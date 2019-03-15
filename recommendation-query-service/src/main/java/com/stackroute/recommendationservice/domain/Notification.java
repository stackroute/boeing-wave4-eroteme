package com.stackroute.recommendationservice.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Notification {
    List<String> emails;
    String question;
}
