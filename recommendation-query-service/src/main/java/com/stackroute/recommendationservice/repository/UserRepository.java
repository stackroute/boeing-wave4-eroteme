package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User,String> {
    @Query("Match(u:USER),(p:parents),(q:Question),(a:Answer) WHERE u.username={UserName} AND (q)-[:question_of_topic]->(p) AND (u)-[:follows]->(p) And NOT (q)<-[:answer_of ]-() Return q")
    List<Question> findAllUnansweredQuestion(@Param("UserName") String username);


    @Query("match (u:USER),(t:children),(q:Question) where u.UserName={username} and t.name={topic} and (q)-[:question_of_topic]->(t) return q")
    List<Question> getAllTrendingQuestionsForUser(@Param("username") String username, @Param("topic") String topic);

    @Query("match (u:USER),(c:children),(p:parents),(q:Question) where q.questionId={questionID} and (q)-[:question_of_topic]->(c) and (u)-[:follows]->(c) or (q)-[:question_of_topic]->(p) and (u)-[:follows]->(p)  return u ")
    List<User> findAllUsersOfTopic(@Param("questionID") long questionID);
}

