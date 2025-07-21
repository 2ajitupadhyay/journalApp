package com.ajit.journalApp.Services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ajit.journalApp.Model.UserModel;
import com.ajit.journalApp.Repository.UserRepository;

@Component
public class UserService{
    
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserModel> getUser(ObjectId id){
        return userRepository.findById(id);
    }

    public void submitUser(UserModel user){
        userRepository.save(user);
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }
    public UserModel findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}