package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Question;
import com.stackroute.domain.UserCurrent;
import com.stackroute.service.UserProfileService;
import org.junit.Before;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@WebMvcTest
@RunWith(SpringRunner.class)
public class UserProfileControllerTest {
    public static final String SUCCESS = "SUCCESS";
    public static final Question QUESTION = Question.builder().build();
    private static final String EMAIL_ID = "EMAIL_ID";
    public static final UserCurrent USER_CURRENT = UserCurrent.builder().emailaddress(EMAIL_ID).build();
    @MockBean
    private UserProfileService userProfileService;
    @Autowired
    private UserProfileController userProfileController;
    @Autowired
    private MockMvc mockMvc;

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
    }

    @Test
    public void testForAddingQuestion() throws Exception {
        when(userProfileService.addQuestionToDB(EMAIL_ID, QUESTION)).thenReturn(SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.put("/question/EMAIL_ID", QUESTION)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForAddingAnswer() throws Exception {
        when(userProfileService.addQuestionToDB(EMAIL_ID, QUESTION)).thenReturn(SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.put("/answer/EMAIL_ID", QUESTION)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(QUESTION)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForGettingUserDetails() throws Exception {
        when(userProfileService.returnAllInfoFromDb(EMAIL_ID)).thenReturn(USER_CURRENT);
        mockMvc.perform(MockMvcRequestBuilders.get("/getall/EMAIL_ID")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(USER_CURRENT)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForNoUserDetailsPresent() throws Exception {
        when(userProfileService.returnAllInfoFromDb(EMAIL_ID)).thenReturn(UserCurrent.builder().build());
        mockMvc.perform(MockMvcRequestBuilders.get("/getall/EMAIL_ID")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(UserCurrent.builder().build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}