package com.stackroute.recommendationservice.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NotificationDTO {
    List<String> emails;
    Question question;
}
