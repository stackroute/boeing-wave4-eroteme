package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Question;
import com.stackroute.domain.UserCurrent;
import com.stackroute.domain.UserDTO;
import com.stackroute.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }


    @Override
    public String addQuestionToDB(String emailid, Question question) {
        boolean flag=true;
        UserCurrent userCurrent=userProfileRepository.findById(emailid).get();
        String givenQuestion=question.getQuestion();
        List<Question> mongoQues=userCurrent.getQuestions();

        for(int i=0;i<mongoQues.size();i++){
            if(mongoQues.get(i).getQuestion()==givenQuestion) {
                mongoQues.set(i, question);
                flag = false;
                break;
            }
        }
        if(flag==true){
            mongoQues.add(question);
        }
        userCurrent.setQuestions(mongoQues);
        userProfileRepository.save(userCurrent);
        return "Success";
    }

    @Override
    public String addAnswerToDb(String emailid, Question question) {
        boolean flag=true;
        UserCurrent userCurrent=userProfileRepository.findById(emailid).get();
        String givenQuestion=question.getQuestion();
        List<Question> mongoAns=userCurrent.getAnswers();

        for(int i=0;i<mongoAns.size();i++){
            if(mongoAns.get(i).getQuestion()==givenQuestion) {
                mongoAns.set(i, question);
                flag = false;
                break;
            }
        }
        if(flag==true){
            mongoAns.add(question);
        }
        userCurrent.setQuestions(mongoAns);
        userProfileRepository.save(userCurrent);
        return "Success";
    }

    @Override
    public UserCurrent returnAllInfoFromDb(String emailid) {
        UserCurrent userCurrent=userProfileRepository.findById(emailid).get();
        return userCurrent;
    }

    @Override
    public UserCurrent addnewUser(UserDTO userDTO) {
        List<Question> questionList = new ArrayList<>();
        List<Question> answerList = new ArrayList<>();
        UserCurrent userCurrent = new UserCurrent(userDTO.getEmail(),userDTO.getFirstName(),0,userDTO.getInterests(),0,questionList,answerList,"imageurl");
        return userProfileRepository.save(userCurrent);
    }
}
