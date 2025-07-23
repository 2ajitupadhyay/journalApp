package com.ajit.journalApp.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ajit.journalApp.Model.JournalModel;
import com.ajit.journalApp.Model.UserModel;
import com.ajit.journalApp.Repository.JournalRepository;

@Component
public class JournalService {
    
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    public List<JournalModel> getAllJournals(){
        return journalRepository.findAll();
    }

    public Optional<JournalModel> getJournal(ObjectId id){
        return journalRepository.findById(id);
    }

    @Transactional
    public void saveJournal(JournalModel journal, String userName){
        try {
            UserModel user = userService.findByUserName(userName);
            journal.setDate(LocalDateTime.now());
            JournalModel saved = journalRepository.save(journal); 
            user.getUserJournals().add(saved);
            userService.submitUser(user);//when the Id is same then mongoDB just updates the entry instead of adding a new one in the DB
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void deleteJournal(ObjectId id, String userName){
        UserModel user = userService.findByUserName(userName);
        user.getUserJournals().removeIf(x -> x.getId().equals(id));
        userService.submitUser(user);
        journalRepository.deleteById(id);
    }

    public void deleteJournalFix(ObjectId id) {
        journalRepository.deleteById(id);
    }

    public void saveJournal(JournalModel journal) {
        journalRepository.save(journal);
    }
}
