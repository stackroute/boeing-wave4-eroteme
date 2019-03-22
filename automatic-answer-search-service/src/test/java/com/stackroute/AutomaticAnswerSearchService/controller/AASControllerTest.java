package com.stackroute.AutomaticAnswerSearchService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.automaticanswersearchservice.Repository.AASRepoImpl;
import com.stackroute.automaticanswersearchservice.model.Answer;
import com.stackroute.automaticanswersearchservice.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AASControllerTest {

    private static final Question question = Question.builder().questionId(234).question("this is good").description("need to describe").build();
    //    List<String> answer = Arrays.asList("pipes", "forms");
    Answer this_is_answer = Answer.builder().answer("this is answer").accepted(true).timestamp(1523).upvotes(234).build();
    List<Question> questions = new ArrayList<>();

    @MockBean
    private AASRepoImpl aasRepo;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getalldata() throws Exception {
        when(aasRepo.findAll()).thenReturn(Collections.singletonList(question));
        mockMvc.perform(MockMvcRequestBuilders.get("/all")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(questions)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


}
