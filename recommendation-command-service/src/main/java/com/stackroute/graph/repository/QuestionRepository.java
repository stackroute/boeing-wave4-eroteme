package com.stackroute.graph.repository;

import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.Question;
import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface QuestionRepository extends Neo4jRepository<Question, Integer> {


    //method to get QUESTIONS//
    @Query("MATCH (m:Question) <-[ASKED]-(u:User) RETURN m,u")
    Collection<Question> getAllQuestions();

    //method to create relationship VIEWED between user and question//
    @Query("match (q:User),(t:Question) where q.userId={userid} and t.questionId={questionid} create (q)-[r:VIEWED]->(t)")
    User userviewedquestionrelationship(@Param("userid") int userId, @Param("questionid") int questionId);


    //method to create relationship ANSWER_OF between answer and question//
    @Query("match (q:Answer),(t:Question) where q.answerId={answerid} and t.questionId={questionid} create (q)-[r:ANSWER_OF]->(t)")
    Answer answerisanswerofquestionrelationship(@Param("answerid") int answerId, @Param("questionid") int questionId);


    //method to create relationship ASKED between user and question//
    @Query("match (q:User),(t:Question) where q.userId={userid} and t.questionId={questionid} create (q)-[r:ASKED]->(t)")
    User useraskedquestionrelationship(@Param("userid") int userId, @Param("questionid") int questionId);


    //method to create relationship UPVOTE between user and question//
    @Query("match (q:User),(t:Question) where q.userId={userid} and t.questionId={questionid} create (q)-[r:UPVOTE]->(t)")
    User userupvotequestionrelationship(@Param("userid") int userId, @Param("questionid") int questionId);


    //method to create relationship DOWNVOTE between user and question//
    @Query("match (q:User),(t:Question) where q.userId={userid} and t.questionId={questionid} create (q)-[r:DOWNVOTE]->(t)")
    User userdownvotequestionrelationship(@Param("userid") int userId, @Param("questionid") int questionId);
}
