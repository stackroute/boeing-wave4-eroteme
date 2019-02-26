package com.stackroute.searchservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    //private member variable declaration//
    private String question;
    private String description;
    private int upvotes;
    private long timestamp;
    private int downvotes;
    List<Answers> answers;
    List<Comments> comments;
}
