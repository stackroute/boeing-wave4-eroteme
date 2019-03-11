package com.stackroute.nlp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.nlp.domain.Nlp;
import com.stackroute.nlp.service.NlpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class NlpControllerTest {

    String question;

    @Mock
    private AmqpTemplate amqpTemplate;
    @Autowired
    private MockMvc mockMvc;

    private Nlp nlp;
    @MockBean
    private NlpService nlpService;

    @InjectMocks
    private NlpController nlpController;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(nlpController).build();
        question = "what is angular";

    }

    @Test(expected = IllegalArgumentException.class)
    public void saveTrack() throws Exception {
        when(nlpService.setquestion(anyString())).thenReturn(question);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/{question}")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(question)))
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}