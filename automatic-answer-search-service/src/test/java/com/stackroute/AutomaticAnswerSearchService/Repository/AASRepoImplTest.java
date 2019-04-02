package com.stackroute.AutomaticAnswerSearchService.Repository;


import com.stackroute.automaticanswersearchservice.Repository.AASRepoImpl;
import com.stackroute.automaticanswersearchservice.model.Answer;
import com.stackroute.automaticanswersearchservice.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AASRepoImplTest {

    private static final Question question = Question.builder().questionId(234).question("this is good").description("need to describe").build();
    //    List<String> answer = Arrays.asList("pipes", "forms");
    Answer this_is_answer = Answer.builder().answer("this is answer").accepted(true).timestamp(1523).upvotes(234).build();
    @Mock
    private AASRepoImpl aasRepo;


    @Test
    public void findAll() {
        when(aasRepo.findAll()).thenReturn(Collections.singletonList(question));
        assertThat(aasRepo.findAll()).containsOnly(question);
    }


}
