package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository {
    Map<Long,TimeEntry> timeRepo = new HashMap<>();


    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeRepo.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeRepo.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeRepo.put(id,timeEntry);
        return timeEntry;
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
