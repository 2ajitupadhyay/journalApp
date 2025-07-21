package com.ajit.journalApp.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.journalApp.Model.UserModel;
import com.ajit.journalApp.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(){
        
        List<UserModel> users = userService.getAllUsers();
        if(users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable ObjectId id){
        Optional<UserModel> user = userService.getUser(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/submit-user")
    public ResponseEntity<UserModel> submitUser(@RequestBody UserModel user){
        try{
            userService.submitUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-user/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserModel user, @PathVariable String userName){
        UserModel original = userService.findByUserName(userName);
        if(original != null){
            original.setUserName(user.getUserName());
            original.setUserPassword(user.getUserPassword());
        }
        userService.submitUser(original);
        return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable ObjectId id){
        // ObjectId objectId = new ObjectId(id);This would work when the data type of id is other than ObjectId
        
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}