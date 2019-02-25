package com.stackroute.graph.service;

import com.stackroute.graph.model.User;
import com.stackroute.graph.repository.AnswerRepository;
import com.stackroute.graph.repository.QuestionRepository;
import com.stackroute.graph.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecommendationCommandServiceImplTest {

    private static final User USER = User.builder().userName("kate").reputation(1).build();

    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private RecommendationCommandServiceImpl recommendationCommandService;

    @Before
    public void setUp() {
    }


    @Test
    public void saveUserToDb() {
        when(userRepository.save(USER)).thenReturn(USER);
        assertThat(recommendationCommandService.saveUserToDb(USER)).isEqualTo(USER);
    }


//    public void saveUserToDb(User user)
//        userRepository.save(user);
//    }


//    @Test
//    public void getUsers(){
//    }
//
//    @Test
//    public void saveQuestionToDb() {
//    }
//
//    @Test
//    public void getQuestions() {
//    }
//
//    @Test
//    public void saveAnswerToDb() {
//    }
//
//    @Test
//    public void getAnswers() {
//    }
//
//    @Test
//    public void deleteAnswers() {
//    }
//
//    @Test
//    public void userfollowstopic() {
//    }
//
//    @Test
//    public void getByUser() {
//    }
//
//    @Test
//    public void questionbelongstopic() {
//    }
//
//    @Test
//    public void useransweredanswer() {
//    }
//
//    @Test
//    public void userviewedquestion() {
//    }
//
//    @Test
//    public void answerisanswerofquestion() {
//    }
//
//    @Test
//    public void useraskedquestion() {
//    }
//
//    @Test
//    public void useracceptedanswer() {
//    }
//
//    @Test
//    public void userupvotedanswer() {
//    }
//
//    @Test
//    public void userdownvotedanswer() {
//    }
//
//    @Test
//    public void userupvotequestion() {
//    }
//
//    @Test
//    public void userdownvotequestion() {
//    }
//}
}