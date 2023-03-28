package ru.academy.Calendarapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.academy.Calendarapp.entity.Category;
import ru.academy.Calendarapp.entity.Event;
import ru.academy.Calendarapp.entity.User;
import ru.academy.Calendarapp.exception.NoEventUserException;
import ru.academy.Calendarapp.exception.NoSuchEventException;
import ru.academy.Calendarapp.repository.EventRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(User user, LocalDate date,
                             String description, String categoryName) {
        Category category = Arrays.stream(Category.values())
                .filter(c -> c.name().equals(categoryName))
                .findFirst()
                .orElse(Category.OTHER);
        return eventRepository.save(Event
                .builder()
                .user(user)
                .date(date)
                .description(description)
                .category(category)
                .build());
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEventsByUserId(Long userId) {
        return eventRepository.getEventByUser_Id(userId)
                .orElseThrow(() -> new NoEventUserException("The user has no events"));
    }

    public List<Event> getEventsByBetweenDatesAndUser(LocalDate start, LocalDate end, Long id) {
        return eventRepository.findByDateBetweenAndUser_Id(start, end, id)
                .orElseThrow(() -> new NoEventUserException("The user has no events"));
    }

    public Event getEventById(Long id){
        return eventRepository.getEventById(id)
                .orElseThrow(()->new NoSuchEventException("There is no event with such id"));
    }

    public void saveEvent(Event event){
        eventRepository.save(event);
    }
}
