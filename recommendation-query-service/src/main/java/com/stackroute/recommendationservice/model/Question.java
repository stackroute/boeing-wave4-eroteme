package com.stackroute.recommendationservice.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@Builder
public class Question {
    @Id
    long questionId;
    String question;
    long timestamp;
    int upvote;
    int downvote;
}
