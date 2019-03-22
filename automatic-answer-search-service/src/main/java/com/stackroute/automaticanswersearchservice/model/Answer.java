package com.stackroute.automaticanswersearchservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Answer implements Serializable {
    User user;
    List<Comment> comments;
    private String answer;
    private boolean accepted;
    private int upvotes;
    private int downvotes;
    private int views;
    private long timestamp;
}
