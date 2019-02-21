package com.stackroute.graph.repository;

import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User,Integer> {


    @Query("MATCH (m:User) RETURN m")
    Collection<User> getAllUsers();

    @Query("MATCH (a:User),(b:Answer) WHERE a.answerId =b.answerId  CREATE (a)-[r:ANSWERED]->(b)")
    Collection<User> makeRelation();
}
