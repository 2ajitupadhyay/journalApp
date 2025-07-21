package com.ajit.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ajit.journalApp.Model.JournalModel;

public interface JournalRepository extends MongoRepository<JournalModel, ObjectId>{
    
}
