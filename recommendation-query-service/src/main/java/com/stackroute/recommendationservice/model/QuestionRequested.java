package com.stackroute.recommendationservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class QuestionRequested {
    @Id
    private long questionId;
    private String question;
    private String description;
    private List<String> topics;
    private int upvotes;
    private long timestamp;
    private int downvote;
    private UserRequested userDocument;
    private List<CommentRequested> commentDocuments;
    private List<AnswerRequested> answerDocuments;
}
