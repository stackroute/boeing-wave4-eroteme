package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.AnswerNode;
import com.stackroute.recommendationcommandservice.model.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AnswerRepository extends Neo4jRepository<AnswerNode, String> {

    //method to get ANSWERS//
    @Query("MATCH (m:AnswerNode) RETURN m")
    Collection<AnswerNode> getAllAnswers();


    //method to create relationship ANSWERED between userDTO and answerDTO//
    @Query("match (q:UserNode),(t:AnswerNode) where q.username={username} and t.answer={answerstring} create (q)-[r:answered]->(t) return q")
    UserNode userAnsweredAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);


    //    //method to create relationship ACCEPTED between userDTO and answerDTO//
    @Query("match (q:UserNode),(t:AnswerNode) where q.username={username} and t.answer={answerstring} create (q)-[r:accepted]->(t)")
    UserNode userAcceptedAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);


    //method to create relationship UPVOTED between userDTO and answerDTO//
    @Query("match (q:UserNode),(t:AnswerNode) where q.username={username} and t.answer={answerstring} create (q)-[r:upvoted]->(t)")
    UserNode userUpvotedAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);


    //method to create relationship DOWNVOTED between userDTO and answerDTO//
    @Query("match (q:UserNode),(t:AnswerNode) where q.username={username} and t.answer={answerstring} create (q)-[r:downvoted]->(t)")
    UserNode userDownvotedAnswerRelationship(@Param("username") String userName, @Param("answerstring") String answerString);
}


