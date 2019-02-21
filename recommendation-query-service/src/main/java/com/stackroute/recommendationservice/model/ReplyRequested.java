package com.stackroute.recommendationservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("reply")
public class ReplyRequested {
    UserRequested userRequested;
    private String reply;
    private long likes;
    private long timestamp;
}