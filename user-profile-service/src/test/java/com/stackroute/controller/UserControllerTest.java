package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//controller class
@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    User user;

    @MockBean
    UserService userService;

    @MockBean
    PasswordEncoder passwordEncoder;
    @InjectMocks
    UserController userController;

    private List<User> list = null;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User();
        user.setFirstName("zakir");
        user.setLastName("hussain");
        user.setEmail("s.zakirhussain02@gmail.com");
        user.setPassword("qwertyuiop");
        user.setInterests(Arrays.asList("dfds","add"));
        list = new ArrayList();
        list.add(user);
    }

    @Test
    public void saveUser() throws Exception {
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print());
    }

    @Test
    public void saveUserFailure() throws Exception {
        when(userService.saveUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(print());
    }


    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
