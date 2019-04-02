package com.stackroute.service;

import com.stackroute.domain.*;
import com.stackroute.repository.UserProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceImplTest {
    private static final String EMAIL_ID = "EMAIL_ID";
    private static final String TEST_QUESTION = "TEST_QUESTION";
    private static final String TEST_ANSWER = "TEST_ANSWER";
    private static final Answer ANSWER = Answer.builder().answer(TEST_ANSWER).user(User.builder().email(EMAIL_ID).build()).build();
    private static final String SUCCESS = "Success";
    private List<Answer> answerList = new ArrayList<>();
    private List<Question> questionList = new ArrayList<>();
    @Mock
    private UserProfileRepository userProfileRepository;
    @InjectMocks
    private UserProfileServiceImpl userProfileService;


    @Before
    public void setUp() {


    }


    @Test
    public void testForAddingQuestionToDb() {
        answerList.add(ANSWER);
        Question question = Question.builder().question(TEST_QUESTION).answer(answerList).build();
        questionList.add(question);
        UserCurrent userCurrent = UserCurrent.builder()
                .emailaddress(EMAIL_ID)
                .questions(questionList)
                .answers(questionList)
                .build();
        when(userProfileRepository.findById(EMAIL_ID)).thenReturn(Optional.of(userCurrent));
        when(userProfileRepository.save(userCurrent)).thenReturn(userCurrent);
        when(userProfileRepository.findById(EMAIL_ID.trim())).thenReturn(Optional.of(userCurrent));
        assertThat(userProfileService.addQuestionToDB(EMAIL_ID, question)).isEqualTo(SUCCESS);
    }

    @Test
    public void testForGettingAllInfoFromDb() {
        UserCurrent userCurrent = UserCurrent.builder().build();
        when(userProfileRepository.findById(EMAIL_ID)).thenReturn(Optional.of(userCurrent));
        assertThat(userProfileService.returnAllInfoFromDb(EMAIL_ID)).isEqualTo(userCurrent);
    }

    @Test
    public void testForAddingUser() {
        UserCurrent userCurrent = UserCurrent.builder().emailaddress(EMAIL_ID)
                .questions(Collections.emptyList()).answers(Collections.emptyList())
                .interests(Collections.emptyList()).reputation(0)
                .username("FIRST_NAME").views(0).imageURL("imageurl").build();
        when(userProfileRepository.save(userCurrent)).thenReturn(userCurrent);
        assertThat(userProfileService.addnewUser(UserDTO.builder().email(EMAIL_ID).firstName("FIRST_NAME").interests(Collections.emptyList())
                .lastName("LAST_NAME").password("PASSWORD").build())).isEqualTo(userCurrent);
    }
}