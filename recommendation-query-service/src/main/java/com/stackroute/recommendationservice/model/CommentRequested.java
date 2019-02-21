package com.stackroute.recommendationservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("comment")
public class CommentRequested {
    UserRequested userRequested;
    List<ReplyRequested> replies;
    private String reply;
    private long timestamp;
    private long likes;
}