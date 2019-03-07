package com.stackroute.recommendationcommandservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerNode {
    @Id
    private String answer;
    private boolean accepted;
    @Relationship(type = "answered", direction = Relationship.INCOMING)
    private List<UserNode> userNode;
    @Relationship(type = "upvoted", direction = Relationship.INCOMING)
    private List<UserNode> userNode2;
    @Relationship(type = "downvoted", direction = Relationship.INCOMING)
    private List<UserNode> userNode3;

}


