package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User,String> {
    @Query("Match(u:USER),(p:parents),(q:Question),(a:Answer) WHERE u.username={UserName} AND (q)-[:question_of]->(p) AND (u)-[:follows]->(p) And NOT (q)<-[:answer_of ]-() Return q")
    List<Question> findAllUnansweredQuestion(@Param("UserName") String username);


//    @Query("")
//    List<Question> getAllTrendingQuestionsForUser(String username);
//
//    @Query("")
//    List<User> findAllUsers();
}

