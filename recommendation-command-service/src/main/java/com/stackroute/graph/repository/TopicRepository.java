package com.stackroute.graph.repository;

import com.stackroute.graph.model.Topic;
import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface TopicRepository extends Neo4jRepository<Topic, Integer> {


//    @Query("CREATE (a:User)-[r:FOLLOWS]->(b:Topic) where b.topicId={topicid} RETURN a")
//    User createRelatioshipBetweenUserAndTopic(@Param("topicid") int topicId);


    @Query("match (q:User),(t:Topic) where q.userId={userid} and t.topicId={topicid} create (q)-[r:FOLLOWS]->(t)")
    User createRelatioshipBetweenUserAndTopic(@Param("userid") int userId, @Param("topicid") int topicId);
}





