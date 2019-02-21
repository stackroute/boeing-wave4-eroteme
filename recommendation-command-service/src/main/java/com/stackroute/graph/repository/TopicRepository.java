package com.stackroute.graph.repository;

import com.stackroute.graph.model.Topic;
import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface TopicRepository extends Neo4jRepository<Topic, Integer> {


    @Query("CREATE (a:User)-[r:FOLLOWS]->(b:Topic) Where b.topicId=(topicid) RETURN type(r)")
    Collection<List> createRelatioshipBetweenUserAndTopic(@Param("user") User user, @Param("topicid") int topicId);

//    @Query("CREATE (u:{user})-[:follows]->(t:topic) where t.topicId={topicid}")
//    boolean createRelatioshipBetweenUserAndTopic(@Param("user") User user, @Param("topicid") int topicid)


}





