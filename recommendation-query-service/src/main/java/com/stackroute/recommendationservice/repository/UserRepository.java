package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Question;
import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Integer> {
    @Query("MATCH (u:user)-[f:follows]->(t:topic) WHERE (t:topic)<-[question_of]-[Q:Question] AND NOT ()-[answer_of]->Question RETURN user")
    List<Question> findAllUnansweredQuestion();


    @Query("match (u:USER),(t:children),(q:Question) where u.UserName={username} and t.name={topic} and (q)-[:question_of_topic]->(t) return q")
    List<Question> getAllTrendingQuestionsForUser(@Param("username") String username, @Param("topic") String topic);
}

