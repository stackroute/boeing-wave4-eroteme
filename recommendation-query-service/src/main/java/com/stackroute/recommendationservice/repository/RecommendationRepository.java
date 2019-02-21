package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.QuestionRequested;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends MongoRepository<QuestionRequested, Long> {

}