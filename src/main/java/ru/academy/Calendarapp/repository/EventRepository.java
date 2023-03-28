package ru.academy.Calendarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.academy.Calendarapp.entity.Event;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<List<Event>> getEventByUser_Id(Long id);

    Optional<List<Event>> findByDateBetweenAndUser_Id(LocalDate startDate, LocalDate endDate, Long id);

    Optional<Event> getEventById(Long id);
}
