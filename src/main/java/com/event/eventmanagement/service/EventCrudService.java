package com.event.eventmanagement.service;


import com.event.eventmanagement.model.Event;
import com.event.eventmanagement.repository.EventCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventCrudService {

    @Autowired
    private EventCrudRepository repo;

    public Event createEvent(Event event) {
        event.setStatus("PENDING");
        return repo.save(event);
    }

    public List<Event> getAllEvents() {
        return repo.findAll();
    }

    public List<Event> getApprovedEvents() {
        return repo.findByStatus("APPROVED");
    }

    public String approveEvent(Long id) {
        Optional<Event> opt = repo.findById(id);
        if (opt.isPresent()) {
            Event e = opt.get();
            e.setStatus("APPROVED");
            repo.save(e);
            return "Approved";
        }
        return "Event not found";
    }

    public String rejectEvent(Long id) {
        Optional<Event> opt = repo.findById(id);
        if (opt.isPresent()) {
            Event e = opt.get();
            e.setStatus("REJECTED");
            repo.save(e);
            return "Rejected";
        }
        return "Event not found";
    }
}
