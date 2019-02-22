package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User,String> {
    @Query("Match(u:USER),(p:children),(q:Question),(a:Answer) WHERE u.UserName={UserName} AND (q)-[:question_of_topic]->(p) AND (u)-[:follows]->(p) And NOT (q)<-[:answer_of ]-() Return q")
    List<Question> findAllUnansweredQuestion(@Param("UserName") String username);


    @Query("match (q:Question),(u:USER),(c:children),(p:parents) where u.UserName={username} and (u)-[:follows]->(c) and (q)-[:question_of_topic]->(c) or (u)-[:follows]->(p) and (q)-[:question_of_topic]->(p) return q")
    List<Question> getAllTrendingQuestionsForUser(@Param("username") String username);
}

