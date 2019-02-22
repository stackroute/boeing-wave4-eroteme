package com.stackroute.graph.repository;

import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface AnswerRepository extends Neo4jRepository<Answer, Long> {

    //method to get ANSWERS//
    @Query("MATCH (m:Answer) RETURN m")
    Collection<Answer> getAllAnswers();


    //method to create relationship ANSWERED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userId={userid} and t.answerId={answerid} create (q)-[r:ANSWERED]->(t)")
    User useransweredanswerrelationship(@Param("userid") int userId, @Param("answerid") long answerId);


    //method to create relationship ACCEPTED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userId={userid} and t.answerId={answerid} create (q)-[r:ACCEPTED]->(t)")
    User useracceptedanswerrelationship(@Param("userid") int userId, @Param("answerid") long answerId);


    //method to create relationship UPVOTED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userId={userid} and t.answerId={answerid} create (q)-[r:UPVOTED]->(t)")
    User userupvotedanswerrelationship(@Param("userid") int userId, @Param("answerid") long answerId);


    //method to create relationship DOWNVOTED between user and answer//
    @Query("match (q:User),(t:Answer) where q.userId={userid} and t.answerId={answerid} create (q)-[r:DOWNVOTED]->(t)")
    User userdownvotedanswerrelationship(@Param("userid") int userId, @Param("answerid") long answerId);
}


