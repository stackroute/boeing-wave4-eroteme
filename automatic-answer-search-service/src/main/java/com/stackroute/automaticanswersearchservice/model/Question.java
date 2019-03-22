package com.stackroute.automaticanswersearchservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(value = "latest")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question implements Serializable {
    //private member variables for Question class
    User user;
    List<Comment> comment;
    List<Answer> answer;
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



