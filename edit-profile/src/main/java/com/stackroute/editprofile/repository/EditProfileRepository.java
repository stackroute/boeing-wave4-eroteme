package com.stackroute.editprofile.repository;

import com.stackroute.editprofile.domain.UserCurrent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EditProfileRepository extends MongoRepository<UserCurrent, String> {
}
