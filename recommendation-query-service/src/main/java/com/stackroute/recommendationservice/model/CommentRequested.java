package com.stackroute.recommendationservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class CommentRequested {
    private String reply;
    private long timestamp;
    private long likes;
    UserRequested userRequested;
    List<ReplyRequested> replies;
}