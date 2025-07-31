package com.ajit.journalApp.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ajit.journalApp.Model.UserModel;
import com.ajit.journalApp.Repository.UserRepository;

@Component
public class UserService{
    
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserModel> getUser(ObjectId id){
        return userRepository.findById(id);
    }

    public void submitUser(UserModel user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void submitAdmin(UserModel user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    //NEW METHOD CREATED FOR SAVING WITHOUT ENCODING
    public void saveUser(UserModel user){
        userRepository.save(user);
    }

    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }

    public UserModel findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}