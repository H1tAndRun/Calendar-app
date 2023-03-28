package ru.academy.Calendarapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.academy.Calendarapp.dto.EventDto;
import ru.academy.Calendarapp.entity.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "date", source = "date")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "id", source = "id")
    Event mapEventDtoToEntity(EventDto eventDto);

    @Mapping(target = "date", source = "date")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "id", source = "id")
    EventDto mapEntityToEventDto(Event event);

    @Mapping(target = "date", source = "date")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "id", ignore = true)
    Event updateEvent(EventDto eventDto, @MappingTarget Event event);
}
