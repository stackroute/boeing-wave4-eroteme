package com.stackroute.recommendationcommandservice.repository;

import com.stackroute.recommendationcommandservice.model.AnswerNode;
import com.stackroute.recommendationcommandservice.model.QuestionNode;
import com.stackroute.recommendationcommandservice.model.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends Neo4jRepository<QuestionNode, Integer> {


    //method to get QUESTIONS//
    @Query("MATCH (m:QuestionNode) RETURN m")
    Collection<QuestionNode> getAllQuestions();

    //method to create relationship VIEWED between userDTO and question//
    @Query("match (q:UserNode),(t:QuestionNode) where q.email={username} and t.questionId={questionid} create (q)-[r:viewed]->(t)")
    UserNode userViewedQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    //method to create relationship ANSWER_OF between answerDTO and question//
    @Query("match (q:AnswerNode),(t:QuestionNode) where q.answer={answerstring} and t.questionId={questionid} create (q)-[r:answer_of]->(t)")
    AnswerNode answerIsAnswerOfQuestionRelationship(@Param("answerstring") String answerString, @Param("questionid") int questionId);


    //method to create relationship ASKED between userDTO and question//
    @Query("match (q:UserNode),(t:QuestionNode) where q.email={username} and t.questionId={questionid} create (q)-[r:asked]->(t)")
    UserNode userAskedQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    //method to create relationship UPVOTE between userDTO and question//
    @Query("match (q:UserNode),(t:QuestionNode) where q.email={username} and t.questionId={questionid} create (q)-[r:upvoted]->(t)")
    UserNode userUpvoteQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    //method to create relationship DOWNVOTE between userDTO and question//
    @Query("match (q:UserNode),(t:QuestionNode) where q.email={username} and t.questionId={questionid} create (q)-[r:downvoted]->(t)")
    UserNode userDownvoteQuestionRelationship(@Param("username") String userName, @Param("questionid") int questionId);


    ////method to create relationship BELONGS between question and topic//
    @Query("match (q:QuestionNode),(t:parents) where q.questionId={questionid} and t.name={name} create (q)-[r:question_of_topic]->(t)")
    QuestionNode questionBelongsTopicRelationship(@Param("questionid") int questionId, @Param("name") List<String> name);



}
