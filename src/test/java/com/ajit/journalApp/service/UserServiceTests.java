package com.ajit.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ajit.journalApp.Repository.UserRepository;
// import com.ajit.journalApp.Services.UserService;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private UserService userService;

    // @BeforeAll
    // void setup(){}

    // @BeforeEach
    // void SetUpforEach(){}

    @Disabled
    @Test
    public void findByUserNameTest(){
        assertNotNull(userRepository.findByUserName("raj"));
    }

    @ParameterizedTest
    @CsvSource({
        "raj",
        "varun",
        "amit"
    })
    public void findByUserNameUsingParameter(String userName){
        assertNotNull(userRepository.findByUserName(userName), "failed for " + userName);
    }
    
}
