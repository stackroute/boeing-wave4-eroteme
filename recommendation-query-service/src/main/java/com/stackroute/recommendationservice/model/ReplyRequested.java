package com.stackroute.recommendationservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ReplyRequested {
    private String reply;
    private long likes;
    private long timestamp;
    UserRequested userRequested;
}