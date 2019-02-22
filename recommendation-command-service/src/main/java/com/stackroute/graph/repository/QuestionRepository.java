package com.stackroute.graph.repository;

import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.Question;
import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface QuestionRepository extends Neo4jRepository<Question, Integer> {

    @Query("MATCH (m:Question) <-[ASKED]-(u:User) RETURN m,u")
    Collection<Question> getAllQuestions();


    @Query("match (q:User),(t:Question) where q.userId={userid} and t.questionId={questionid} create (q)-[r:VIEWED]->(t)")
    User createRelatioshipBetweenUserAndQuestion(@Param("userid") int userId, @Param("questionid") int questionId);


    @Query("match (q:Answer),(t:Question) where q.answerId={answerid} and t.questionId={questionid} create (q)-[r:ANSWER_OF]->(t)")
    Answer createRelatioshipBetweenAnswerAndQuestion(@Param("answerid") int answerId, @Param("questionid") int questionId);
}
