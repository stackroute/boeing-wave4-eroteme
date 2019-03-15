package com.stackroute.repository;

import com.stackroute.domain.Question;
import com.stackroute.domain.UserCurrent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserCurrent,String>{

}
