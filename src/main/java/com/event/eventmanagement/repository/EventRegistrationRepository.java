package com.event.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.eventmanagement.model.EventRegistration;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
}
