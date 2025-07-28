package com.ajit.journalApp.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;

@Document(collection = "users")
@Data
// @NoArgsConstructor
public class UserModel {
    @Id
    private ObjectId id;
    @Indexed(unique = true)//We also need to add functionalities in the application.properties file for this
    @NonNull // This implementation is from the lombok, when we try set value to this field using lombok, it will let it be null;
    private String userName;
    @NonNull
    private String userPassword;
    @DBRef
    private List<JournalModel> userJournals = new ArrayList<>();
    private List<String> roles;
}
