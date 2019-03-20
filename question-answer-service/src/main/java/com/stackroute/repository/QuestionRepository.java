package com.stackroute.repository;

import com.stackroute.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, Integer> {
    boolean existsByQuestion(String question);

    Question findByQuestionId(int questionId);

    Optional<Question> findByQuestion(String question);
}
