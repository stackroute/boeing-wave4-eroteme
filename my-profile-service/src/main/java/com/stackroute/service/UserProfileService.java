package com.stackroute.service;

import com.stackroute.domain.Question;
import com.stackroute.domain.UserCurrent;
import com.stackroute.domain.UserDTO;

public interface UserProfileService {
    String addQuestionToDB(String emailid, Question question);

    String addAnswerToDb(String emailid, Question question);

    UserCurrent returnAllInfoFromDb(String emailid);

    UserCurrent addnewUser(UserDTO userDTO);
}
