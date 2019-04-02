package com.stackroute.DTO;

import com.stackroute.service.Actions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//Question DTO sent from question and answer service to notify when answer has been posted or answer has been accepted
public class QuestionDTO {
    private Actions action;
    private int questionId;
    private String question;
    private String description;
    private List<String> topics;
    private int upvotes;
    private long timestamp;
    private int downvotes;
    User user;
    List<Comment> comment;
    List<Answer> answer;
}
