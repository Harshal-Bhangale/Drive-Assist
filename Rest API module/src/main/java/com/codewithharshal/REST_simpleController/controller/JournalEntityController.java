package com.codewithharshal.REST_simpleController.controller;

import com.codewithharshal.REST_simpleController.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // This tell Springboot - Heyy this class will handle HTTP requests and return responses as JSON.
@RequestMapping("/journal") // Base URL Path. - All APIs here will start with: http://localhost:8080/journal
public class JournalEntityController {

    /*
    * This is temporary in-memory database.
    * Long -> Key(Id of the jorunal entry)
    * JournalEntry -> The object containing title and content
    *  HashMap is used to stors entries like :
    * { 1L => JournalEntry1 , 2L => JournalEntry2, ..... }
     */
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping    // localhost:8080/journal GET
    public List<JournalEntry> getAll(){
        /*
        * journalEntries.values() -> Gives all the values(i.e all JournalEntry object)
        * You wrap it in an ArrayList to return as a JSON array.
         */
         return new ArrayList<>(journalEntries.values());
    }

    @PostMapping // localhost:8080/journal POST  // handles POST requests to /journal
    /* Take the JSON form the request body and convert it into a Java Object.
     * */

    public boolean createEntry(@RequestBody JournalEntry myEntry){ // It's like saying, "Heyy spring, please take the Data from the request and turn it into the a java object that I can use in my code
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntyById(@PathVariable long myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable long myId)
    {
        return journalEntries.remove(myId);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        return journalEntries.put(id, myEntry);
    }
}
