package com.ajit.journalApp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_journal")
public class JournalController{

    // private Map<ObjectId, JournalModel> journalList = new HashMap<>();

    // @GetMapping("/getAll")
    // public List<JournalModel> getAll(){
    //     return new ArrayList<JournalModel>(journalList.values());
    // }

    // @GetMapping("/get-journal/{id}")
    // public JournalModel getJournal(@PathVariable long id){
    //     return journalList.get(id);
    // }

    // @PostMapping("/submit")
    // public boolean submit(@RequestBody JournalModel journal){
    //     journalList.put(journal.getId(), journal);
    //     return true;
    // }

    // @DeleteMapping("/delete/{id}")
    // public boolean delete(@PathVariable long id){
    //     journalList.remove(id);
    //     return true;
    // }

    // @PutMapping("/update")
    // public boolean update(@RequestBody JournalModel journal){
    //     journalList.put(journal.getId(), journal);
    //     return true;
    // }

}