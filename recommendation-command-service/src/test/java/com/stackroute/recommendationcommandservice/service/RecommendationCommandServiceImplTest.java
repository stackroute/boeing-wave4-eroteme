//package com.stackroute.recommendationcommandservice.service;
//
//import com.stackroute.recommendationcommandservice.model.AnswerNode;
//import com.stackroute.recommendationcommandservice.model.QuestionNode;
//import com.stackroute.recommendationcommandservice.model.UserNode;
//import com.stackroute.recommendationcommandservice.repository.AnswerRepository;
//import com.stackroute.recommendationcommandservice.repository.QuestionRepository;
//import com.stackroute.recommendationcommandservice.repository.UserRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RecommendationCommandServiceImplTest {
//
//    private static final UserNode USER_NODE = UserNode.builder().username("kate").reputation(1).build();
//    private static final QuestionNode QUESTION_NODE = QuestionNode.builder().questionId(12).question("what is java").timestamp(34544).upvote(32).downvote(1).build();
//    private static final AnswerNode ANSWER_NODE = AnswerNode.builder().answer("Dont do it").accepted(true).build();
//
//
//    @Mock
//    private AnswerRepository answerRepository;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private QuestionRepository questionRepository;
//
//    @InjectMocks
//    private RecommendationCommandServiceImpl recommendationCommandService;
//
//
//    @Test
//    public void saveUserToDb() {
//        when(userRepository.save(USER_NODE)).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.saveUserToDb(USER_NODE)).isEqualTo(USER_NODE);
//    }
//
//
//    @Test
//    public void getUsers() {
//
//        when(userRepository.getAllUsers()).thenReturn(Collections.singletonList(USER_NODE));
//        assertThat(recommendationCommandService.getUsers()).containsOnly(USER_NODE);
//
//    }
//
//
//    @Test
//    public void saveQuestionToDb() {
//        when(questionRepository.save(QUESTION_NODE)).thenReturn(QUESTION_NODE);
//        assertThat(recommendationCommandService.saveQuestionToDb(QUESTION_NODE)).isEqualTo(QUESTION_NODE);
//
//    }
//
//
//    @Test
//    public void getQuestions() {
//
//        when(questionRepository.getAllQuestions()).thenReturn(Collections.singletonList(QUESTION_NODE));
//        assertThat(recommendationCommandService.getQuestions()).containsOnly(QUESTION_NODE);
//
//    }
//
//
//    @Test
//    public void saveAnswerToDb() {
//        when(answerRepository.save(ANSWER_NODE)).thenReturn(ANSWER_NODE);
//        assertThat(recommendationCommandService.saveAnswerToDb(ANSWER_NODE)).isEqualTo(ANSWER_NODE);
//
//    }
//
//    @Test
//    public void getAnswers() {
//        when(answerRepository.getAllAnswers()).thenReturn(Collections.singletonList(ANSWER_NODE));
//        assertThat(recommendationCommandService.getAnswers()).containsOnly(ANSWER_NODE);
//
//    }
//
//    @Test
//    public void userFollowsTopic() {
//        List<String> supplierNames = Arrays.asList("pipes", "forms");
//
//
//        when(userRepository.userFollowsTopicRelationship("meghana", supplierNames)).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userFollowsTopic("meghana", supplierNames)).isEqualTo(USER_NODE);
//
//    }
//
//    @Test
//    public void getByUser() {
//
//        when(userRepository.getByUser(12)).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.getByUser(12)).isEqualTo(USER_NODE);
//
//    }
//
//    @Test
//    public void questionBelongsTopic() {
//        List<String> supplierNames = Arrays.asList("pipes", "forms");
//
//        when(questionRepository.questionBelongsTopicRelationship(12, supplierNames)).thenReturn(QUESTION_NODE);
//        assertThat(recommendationCommandService.questionBelongsTopic(12, supplierNames)).isEqualTo(QUESTION_NODE);
//
//    }
//
//    @Test
//    public void userAnsweredAnswer() {
//        when(answerRepository.userAnsweredAnswerRelationship("srinidhi", "this is good")).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userAnsweredAnswer("srinidhi", "this is good")).isEqualTo(USER_NODE);
//
//    }
//
//    //
//    @Test
//    public void userViewedQuestion() {
//        when(questionRepository.userViewedQuestionRelationship("anirudh", 101)).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userViewedQuestion("anirudh", 101)).isEqualTo(USER_NODE);
//
//    }
//
//    @Test
//    public void answerIsAnswerOfQuestion() {
//        when(questionRepository.answerIsAnswerOfQuestionRelationship("this is good", 101)).thenReturn(ANSWER_NODE);
//        assertThat(recommendationCommandService.answerIsAnswerOfQuestion("this is good", 101)).isEqualTo(ANSWER_NODE);
//
//    }
//
//    @Test
//    public void userAskedQuestion() {
//        when(questionRepository.userAskedQuestionRelationship("kiran", 101)).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userAskedQuestion("kiran", 101)).isEqualTo(USER_NODE);
//
//
//    }
//
//    @Test
//    public void userAcceptedAnswer() {
//        when(answerRepository.userAcceptedAnswerRelationship("gagana", "this is good")).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userAcceptedAnswer("gagana", "this is good")).isEqualTo(USER_NODE);
//
//    }
//
//    @Test
//    public void userUpvotedAnswer() {
//        when(answerRepository.userUpvotedAnswerRelationship("varun", "this is good")).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userUpvotedAnswer("varun", "this is good")).isEqualTo(USER_NODE);
//
//    }
//
//    @Test
//    public void userDownvotedAnswer() {
//        when(answerRepository.userDownvotedAnswerRelationship("harsha Bean", "mysuru mango")).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userDownvotedAnswer("harsha Bean", "mysuru mango")).isEqualTo(USER_NODE);
//
//    }
//
//    @Test
//    public void userUpvoteQuestion() {
//        when(questionRepository.userUpvoteQuestionRelationship("easwar", 2696)).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userUpvoteQuestion("easwar", 2696)).isEqualTo(USER_NODE);
//
//    }
//
//    @Test
//    public void userDownvoteQuestion() {
//
//        when(questionRepository.userDownvoteQuestionRelationship("siddharth", 102)).thenReturn(USER_NODE);
//        assertThat(recommendationCommandService.userDownvoteQuestion("siddharth", 102)).isEqualTo(USER_NODE);
//
//    }
//}