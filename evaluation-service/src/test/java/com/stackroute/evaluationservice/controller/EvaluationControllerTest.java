//package com.stackroute.evaluationservice.controller;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.web.client.RestTemplate;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class EvaluationControllerTest {
//    @MockBean
//    private RabbitTemplate rabbitTemplate;
//    @MockBean
//    private RestTemplate restTemplate;
//    @Autowired
//    private EvaluationController evaluationController;
//
//    @Before
//    public void setUp() {
//        ReflectionTestUtils.setField(evaluationController, "exchange", "sampleexchange");
//        ReflectionTestUtils.setField(evaluationController, "routingKey", "");
//    }
//
//}