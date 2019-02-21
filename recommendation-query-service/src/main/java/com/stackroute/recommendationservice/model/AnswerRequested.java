package com.stackroute.recommendationservice.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class AnswerRequested {
    private String answer;
    private boolean accepted;
    private int upvotes;
    private int views;
    private long timestamp;
    UserRequested userRequested;
    List<CommentRequested> commentRequesteds;
}
