package com.event.eventmanagement.controller;

import com.event.eventmanagement.model.Event;
import com.event.eventmanagement.model.EventRegistration;
import com.event.eventmanagement.repository.EventRepository;
import com.event.eventmanagement.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationRepository registrationRepository;

    // 1. Create Event (Teacher)
    @PostMapping("/createEvent")
    public Event createEvent(@RequestBody Event event) {
        event.setStatus("PENDING");
        return eventRepository.save(event);
    }

    // 2. Get All Events (Admin + Teacher + Student)
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // 3. Register for Event (Student)
    @PostMapping("/register")
    public ResponseEntity<?> registerForEvent(@RequestBody EventRegistration registration) {
        try {
            // Database mein save karega (EventRegistration.java entity ke hisab se)
            EventRegistration saved = registrationRepository.save(registration);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // 4. Get All Registrations (Student Dashboard ke liye)
    @GetMapping("/all")
    public List<EventRegistration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // 5. Approve/Reject (Admin)
    @PutMapping("/approve/{id}")
    public Event approveEvent(@PathVariable Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.setStatus("APPROVED");
        return eventRepository.save(event);
    }
}