package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends Neo4jRepository<UserNode, String> {

    //method to get USER//
    @Query("MATCH (m:UserNode) RETURN m")
    Collection<UserNode> getAllUsers();


    //method to get USER using REPUTATION//
    @Query("match (m:UserNode) where m.reputation={reputation} return m")
    UserNode getByUser(@Param("reputation") int reputation);


    //method to create relationship FOLLOWS between userDTO and topic//
    @Query("match (q:UserNode),(t:parents) where q.username={username} and t.name={name} create (q)-[r:follows]->(t)")
    UserNode userFollowsTopicRelationship(@Param("username") String userName, @Param("name") String topic);
}
