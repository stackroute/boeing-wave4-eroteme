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
public class QuestionNode {

    @Id
    private int questionId;
    private String question;
    private long timestamp;
    private int upvote;
    private int downvote;
    @Relationship(type = "asked", direction = Relationship.INCOMING)
    private List<UserNode> userNode;
    @Relationship(type = "answer_of", direction = Relationship.INCOMING)
    private List<AnswerNode> answerNode;
    @Relationship(type = "viewed", direction = Relationship.INCOMING)
    private List<UserNode> userNode1;
    @Relationship(type = "upvoted", direction = Relationship.INCOMING)
    private List<UserNode> userNode2;
    @Relationship(type = "downvoted", direction = Relationship.INCOMING)
    private List<UserNode> userNode3;

}
