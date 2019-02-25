package com.stackroute.recommendationservice.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("answer")
@Builder
public class AnswerRequested {
    UserRequested userRequested;
    List<CommentRequested> commentRequested;
    private String answer;
    private boolean accepted;
    private int upvotes;
    private int views;
    private long timestamp;
}
