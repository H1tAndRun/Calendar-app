package ru.academy.Calendarapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.academy.Calendarapp.dto.EventDto;
import ru.academy.Calendarapp.facade.EventFacade;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventFacade eventFacade;

    @PostMapping("/event")
    public ResponseEntity<EventDto> createEvent(@RequestHeader String token,
                                                @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventFacade.createEvent(eventDto, token));
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@RequestHeader String token, @PathVariable Long id) {
        eventFacade.deleteEvent(token, id);
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventDto>> getEventByUser(@RequestHeader String token) {
        return ResponseEntity.ok(eventFacade.getEventByUser(token));
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> getEventByUserAndCategory(@RequestHeader String token,
                                                                    @RequestParam String category) {
        return ResponseEntity.ok(eventFacade.getEventByUserAndCategory(token, category));
    }

    @GetMapping("/events/date-range")
    public ResponseEntity<List<EventDto>> getEventsByBetweenDatesAndUser(@RequestHeader String token,
                                                                         @RequestParam("dateStart")
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                         LocalDate dateStart,
                                                                         @RequestParam("dateEnd")
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                         LocalDate dateEnd) {
        return ResponseEntity.ok(eventFacade.getEventsByBetweenDatesAndUser(token, dateStart, dateEnd));
    }

    @PutMapping("/event")
    public ResponseEntity<Long> changeEvent(@RequestHeader String token, @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventFacade.changeEvent(token, eventDto));
    }
}
