//package com.stackroute.service;
//
//import com.stackroute.domain.User;
//import com.stackroute.exceptions.UserAlreadyExistsException;
//import com.stackroute.respository.UserRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
//
//public class UserServiceTest {
//    private User user;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//    List<User> list = null;
//
//    @Before
//    public void setUp() {
//
//        MockitoAnnotations.initMocks(this);
//        user = new User();
//        user.setFirstName("zakir");
//        user.setLastName("hussain");
//        user.setEmail("s.zakirhussain02@gmail.com");
//        user.setPassword("qwertyuiop");
//        user.setInterests(Arrays.asList("dfds","add"));
//        list = new ArrayList();
//        list.add(user);
//    }
//
//    @Test
//    public void saveMuzixTestSuccess() throws UserAlreadyExistsException {
//
//        when(userRepository.save((User) any())).thenReturn(user);
//        User savedUser = userService.saveUser(user);
//        Assert.assertEquals(user, savedUser);
//
//        //verify here verifies that userRepository save method is only called once
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void saveUpdateTestFailure() throws UserAlreadyExistsException {
//        when(userRepository.save((User) any())).thenReturn(user);
//        User savedUser = userService.saveUser(user);
//        System.out.println("saved" + savedUser);
//        Assert.assertEquals(user, savedUser);
//
//    }
//}
