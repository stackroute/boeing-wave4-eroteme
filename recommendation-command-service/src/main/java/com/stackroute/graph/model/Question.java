package com.stackroute.graph.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    private int questionId;
    private String questionString;
    private int timestamp;
    private int upVote;
    private int downVote;
    @Relationship(type = "ASKED", direction = Relationship.INCOMING)
    private List<User> user;
    @Relationship(type = "ANSWER_OF", direction = Relationship.INCOMING)
    private List<Answer> answer;
    @Relationship(type = "VIEWED", direction = Relationship.INCOMING)
    private List<User> user1;
    @Relationship(type = "UPVOTED", direction = Relationship.INCOMING)
    private List<User> user2;
    @Relationship(type = "DOWNVOTED", direction = Relationship.INCOMING)
    private List<User> user3;


}
