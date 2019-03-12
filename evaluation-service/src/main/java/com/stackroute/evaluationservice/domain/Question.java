package com.stackroute.evaluationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("question")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    User user;
    List<Comment> comment;
    List<Answer> answer;
    @Id
    private int questionId;
    private String question;
    private String description;
    private List<String> topics;
    private int upvotes;
    private long timestamp;
    private int downvotes;
}
