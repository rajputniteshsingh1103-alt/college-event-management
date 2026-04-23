package com.event.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.event.eventmanagement.model.Event;
import com.event.eventmanagement.model.EventRegistration;
import com.event.eventmanagement.repository.EventRepository;
import com.event.eventmanagement.repository.EventRegistrationRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationRepository registrationRepository;

    public Event updateStatus(Long id, String status) {
        Event event = eventRepository.findById(id).orElse(null);

        if (event != null) {
            event.setStatus(status);
            return eventRepository.save(event);
        }

        return null;
    }

    // ✅ Event create
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // ✅ Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // ✅ Register event
    public EventRegistration save(EventRegistration event) {
        return registrationRepository.save(event);
    }

    // ✅ Get all registrations
    public List<EventRegistration> getAll() {
        return registrationRepository.findAll();
    }
}