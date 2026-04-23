package com.event.eventmanagement.repository;


import com.event.eventmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventCrudRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(String status);
}
