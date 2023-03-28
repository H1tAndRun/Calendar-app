package ru.academy.Calendarapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.academy.Calendarapp.dto.UserInfoDto;
import ru.academy.Calendarapp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "password", source = "password")
    UserInfoDto mapUserToUserInfoDto(User user);
}
