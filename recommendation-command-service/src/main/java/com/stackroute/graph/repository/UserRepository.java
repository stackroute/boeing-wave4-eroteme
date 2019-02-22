package com.stackroute.graph.repository;

import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User,Integer> {

    //method to get USER//
    @Query("MATCH (m:User) RETURN m")
    Collection<User> getAllUsers();


    //method to get USER using REPUTATION//
    @Query("match (m:User) where m.reputation={reputation} return m")
    User getByUser(@Param("reputation") int reputation);
}
