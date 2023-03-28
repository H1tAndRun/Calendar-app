package ru.academy.Calendarapp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EventDto {

    private Long id;

    private LocalDate date;

    private String description;

    private String category;
}
