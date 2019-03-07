package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.domain.QuestionNode;
import com.stackroute.recommendationservice.domain.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<UserNode, String> {
    @Query("Match(u:User),(p:parents),(q:Question) WHERE u.userName={UserName} AND (q)-[:question_of_topic]->(p) AND (u)-[:follows]->(p) And NOT (q)<-[:answer_of ]-() Return q")
    List<QuestionNode> findAllUnansweredQuestionsForRegisteredUser(@Param("UserName") String username);

    @Query("match (u:User),(c:children),(p:parents),(q:Question) where q.questionId={questionID} and (q)-[:question_of_topic]->(c) and (u)-[:follows]->(c) or (q)-[:question_of_topic]->(p) and (u)-[:follows]->(p)  return u ")
    List<UserNode> findAllUsersRelatedToTopic(@Param("questionID") long questionID);

    @Query("match (q:Question),(u:User),(c:children),(p:parents) where u.userName={username} and (u)-[:follows]->(c) and (q)-[:question_of_topic]->(c) or (u)-[:follows]->(p) and (q)-[:question_of_topic]->(p) return q")
    List<QuestionNode> getAllTrendingQuestionsForRegisteredUser(@Param("username") String username);

    @Query("Match (u:User),(q:Question),(a:Answer),(c:children),(p:parents) where u.userName={username} and (u)-[:follows]->(c) or (u)-[:follows]->(p) and (q)-[:question_of_topic]->(c) or (q)-[:question_of_topic]->(p)  and (a)-[:answer_of]->(q) and a.accepted=true return q")
    List<QuestionNode> getAllAcceptedAnswersForDomain(@Param("username") String username);
}

