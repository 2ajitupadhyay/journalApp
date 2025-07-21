package com.ajit.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajit.journalApp.Model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, ObjectId>{
    UserModel findByUserName(String userName);
}