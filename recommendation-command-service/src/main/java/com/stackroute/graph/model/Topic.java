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
public class Topic {
    @Id
    private int topicId;
    private String topics;
    @Relationship(type = "SUBTOPIC", direction = Relationship.INCOMING)
    private Topic topic;
    @Relationship(type = "FOLLOWS", direction = Relationship.INCOMING)
    private List<User> user;
    @Relationship(type = "BELONGS", direction = Relationship.INCOMING)
    private List<Question> question;
}
