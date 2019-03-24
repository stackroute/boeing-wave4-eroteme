package com.stackroute.repository;

import com.stackroute.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QuestionRepository extends MongoRepository<Question, Integer> {
    boolean existsByQuestion(String question);

    Question findByQuestionId(int questionId);

    @Query("{'question':?0}")
    Question findByQuestion(String question);
}
