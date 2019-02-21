package com.stackroute.repository;

import com.stackroute.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question,String> {
    boolean existsByQuestion(String question);
    Question findByQuestionId(int questionId);
}
