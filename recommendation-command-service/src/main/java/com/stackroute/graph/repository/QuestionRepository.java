package com.stackroute.graph.repository;

import com.stackroute.graph.model.Question;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface QuestionRepository extends Neo4jRepository<Question, Integer> {

    @Query("MATCH (m:Question) <-[ASKED]-(u:User) RETURN m,u")
    Collection<Question> getAllQuestions();

}
