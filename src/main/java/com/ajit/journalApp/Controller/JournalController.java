package com.ajit.journalApp.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.journalApp.Model.JournalModel;
import com.ajit.journalApp.Model.UserModel;
import com.ajit.journalApp.Services.JournalService;
import com.ajit.journalApp.Services.UserService;

@RestController
@RequestMapping("/journal")
public class JournalController {
    
    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllJournalsOfUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        
        UserModel user = userService.findByUserName(userName);
        List<JournalModel> list = user.getUserJournals();

        if(list != null && !list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity<JournalModel> getJournal(@PathVariable ObjectId id){
        
        // For NOT_FOUND request, the requst endpoints must be valid like the id length must be of 24 character(ObjectId)
        // or else the the method will give exception and the desired response will not be received
        // We can also handle this exception in the method for the incorrect objectID provided

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserModel user = userService.findByUserName(userName);
        
        List<JournalModel> collect = user.getUserJournals().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalModel> journal = journalService.getJournal(id);

            if(journal.isPresent()){
                return new ResponseEntity<>(journal.get(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/submit-journal")
    public ResponseEntity<JournalModel> submitJournalForUser(@RequestBody JournalModel journal){
        try {
            // UserModel user = userService.findByUserName(userName);
            // user.getUserJournals().add(journal);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            journalService.saveJournal(journal, userName);

            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    // @DeleteMapping("/delete-id/{id}")
    // public ResponseEntity<?> deleteFix(@PathVariable ObjectId id){
    //     journalService.deleteJournalFix(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

    @DeleteMapping("/delete-id/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        boolean removed = journalService.deleteJournal(id, userName);

        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update-id/{id}")
    public ResponseEntity<?> update(
        @RequestBody JournalModel journal,
        @PathVariable ObjectId id
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        UserModel user = userService.findByUserName(userName);

        List<JournalModel> collect = user.getUserJournals().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        JournalModel old = journalService.getJournal(id).orElse(null);

        if (!collect.isEmpty() && old != null) {

            old.setContent((journal.getContent() != null && !journal.getContent().equals("")) ? journal.getContent() : old.getContent());
            old.setTitle((journal.getTitle() != null && !journal.getTitle().equals("")) ? journal.getTitle() : old.getTitle());
            
            journalService.saveJournal(old);
            
            // return new ResponseEntity<>(old, HttpStatus.OK);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
