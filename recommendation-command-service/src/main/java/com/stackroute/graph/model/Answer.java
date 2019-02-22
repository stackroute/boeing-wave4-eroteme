package com.stackroute.graph.model;


import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
@Data
public class Answer {
    @Id
    private long answerId;
    private String answerString;
    @Relationship(type = "ACCEPTED", direction = Relationship.INCOMING)
    private List<User> user;
    @Relationship(type = "ANSWERED", direction = Relationship.INCOMING)
    private List<User> user1;
    @Relationship(type = "UPVOTED", direction = Relationship.INCOMING)
    private List<User> user2;
    @Relationship(type = "DOWNVOTED", direction = Relationship.INCOMING)
    private List<User> user3;

}


