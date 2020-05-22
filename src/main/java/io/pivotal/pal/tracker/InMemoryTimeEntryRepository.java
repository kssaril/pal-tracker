package io.pivotal.pal.tracker;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository {
    Map<Long,TimeEntry> timeRepo = new HashMap<>();
    private  long timeEntryId = 0L ;

    private  synchronized Long incrementTid(){
        this.timeEntryId ++ ;
        return this.timeEntryId ;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id = 0L ;
        if(StringUtils.isEmpty(timeEntry.getId())|| timeEntry.getId() == 0L ) {
            id = incrementTid();
            timeEntry.setId(id);
        }else{
            id = timeEntry.getId();
        }
        timeRepo.put(id,timeEntry);
        return timeRepo.get(id);
    }

    @Override
    public TimeEntry find(Long id) {
        return timeRepo.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(timeRepo.containsKey(id)) {
            timeEntry.setId(id);
            timeRepo.put(id, timeEntry);
        }
        else{
            return null ;
            }
        return timeRepo.get(id);
    }

    @Override
    public void delete(long id) {
        timeRepo.remove(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeRepo.values());
    }
}
