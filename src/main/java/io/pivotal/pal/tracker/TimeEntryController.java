package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeentry = timeEntryRepository.find(id) ;
        if(null == timeentry ){
            return new ResponseEntity<TimeEntry>(timeentry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<TimeEntry>(timeentry, HttpStatus.OK);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        return new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);

    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry>  update(@PathVariable Long id , @RequestBody TimeEntry timeEntry){
        TimeEntry timeEntry1 = timeEntryRepository.update(id,timeEntry);
        if(null == timeEntry1 ){
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<TimeEntry>(timeEntry1, HttpStatus.OK);
        }

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
