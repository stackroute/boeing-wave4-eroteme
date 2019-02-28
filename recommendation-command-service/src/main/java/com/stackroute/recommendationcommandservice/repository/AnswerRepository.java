package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.Answer;
import com.stackroute.recommendationcommandservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AnswerRepository extends Neo4jRepository<Answer, Long> {

    //method to get ANSWERS//
    @Query("MATCH (m:Answer) RETURN m")
    Collection<Answer> getAllAnswers();


    //method to create relationship ANSWERED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerId={answerid} create (q)-[r:answered]->(t)")
    User userAnsweredAnswerRelationship(@Param("username") String userName, @Param("answerid") long answerId);


    //method to create relationship ACCEPTED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerId={answerid} create (q)-[r:accepted]->(t)")
    User userAcceptedAnswerRelationship(@Param("username") String userName, @Param("answerid") long answerId);


    //method to create relationship UPVOTED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerId={answerid} create (q)-[r:upvoted]->(t)")
    User userUpvotedAnswerRelationship(@Param("username") String userName, @Param("answerid") long answerId);


    //method to create relationship DOWNVOTED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userName={username} and t.answerId={answerid} create (q)-[r:downvoted]->(t)")
    User userDownvotedAnswerRelationship(@Param("username") String userName, @Param("answerid") long answerId);
}


