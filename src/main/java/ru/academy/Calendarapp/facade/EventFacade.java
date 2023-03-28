package ru.academy.Calendarapp.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.academy.Calendarapp.dto.EventDto;
import ru.academy.Calendarapp.entity.Event;
import ru.academy.Calendarapp.entity.User;
import ru.academy.Calendarapp.exception.NoEventUserException;
import ru.academy.Calendarapp.mapper.EventMapper;
import ru.academy.Calendarapp.service.EventService;
import ru.academy.Calendarapp.service.UserService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventFacade {

    private final UserService userService;

    private final EventService eventService;

    private final EventMapper eventMapper;


    public EventDto createEvent(EventDto eventDto, String token) {
        User user = userService.getUserByToken(token);
        Event event = eventService.createEvent(user, eventDto.getDate(),
                eventDto.getDescription(),
                eventDto.getCategory());
        return eventMapper.mapEntityToEventDto(event);
    }

    public void deleteEvent(String token, Long idEvent) {
        User user = userService.getUserByToken(token);
        boolean isEventUser = user.getEvents()
                .stream()
                .anyMatch(event -> event.getId().equals(idEvent));
        if (isEventUser) {
            eventService.deleteEventById(idEvent);
        } else {
            throw new NoEventUserException("This event does not belong to the user");
        }
    }

    public List<EventDto> getEventByUser(String token) {
        User user = userService.getUserByToken(token);
        return eventService.getEventsByUserId(user.getId())
                .stream()
                .map(eventMapper::mapEntityToEventDto)
                .collect(Collectors.toList());
    }

    public List<EventDto> getEventByUserAndCategory(String token, String category) {
        User user = userService.getUserByToken(token);
        List<Event> events = eventService.getEventsByUserId(user.getId())
                .stream()
                .filter(event -> event.getCategory().toString().equals(category))
                .collect(Collectors.toList());
        return events
                .stream()
                .map(eventMapper::mapEntityToEventDto)
                .collect(Collectors.toList());
    }

    public List<EventDto> getEventsByBetweenDatesAndUser(String token, LocalDate start, LocalDate end) {
        User user = userService.getUserByToken(token);
        return eventService.getEventsByBetweenDatesAndUser(start, end, user.getId())
                .stream()
                .map(eventMapper::mapEntityToEventDto)
                .collect(Collectors.toList());
    }

    public Long changeEvent(String token, EventDto eventDto) {
        Event event = eventService.getEventById(eventDto.getId());
        User user = userService.getUserByToken(token);
        if (!user.getId().equals(event.getUser().getId())) {
            throw new NoEventUserException("his event does not belong to this user");
        }
        event = eventMapper.updateEvent(eventDto, event);
        eventService.saveEvent(event);
        return event.getId();
    }
}
