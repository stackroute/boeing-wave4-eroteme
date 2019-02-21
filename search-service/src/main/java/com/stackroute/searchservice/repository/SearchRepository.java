package com.stackroute.searchservice.repository;

import com.stackroute.searchservice.domain.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends MongoRepository<Topic,String>{

}
