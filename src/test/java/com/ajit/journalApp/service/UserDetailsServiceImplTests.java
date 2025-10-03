package com.ajit.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import com.ajit.journalApp.Repository.UserRepository;
import com.ajit.journalApp.Services.UserDetailsServiceImpl;

public class UserDetailsServiceImplTests {
    
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        // Find this error on how to create an object of UserModel class here!!
        // when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(UserModel.setUserName("amit").password("12345").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("bhandari");
        assertNotNull(user);
    }
}
