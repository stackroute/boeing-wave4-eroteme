package com.stackroute.recommendationcommandservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    com.stackroute.domain.User user;
    List<com.stackroute.domain.Comment> comment;
    List<com.stackroute.domain.Answer> answer;
    //Variables
    @Id
    private int questionId;
    private String question;
    private String description;
    private List<String> topics;
    private int upvotes;
    private long timestamp;
    private int downvotes;
}
