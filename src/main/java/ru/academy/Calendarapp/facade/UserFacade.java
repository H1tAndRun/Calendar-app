package ru.academy.Calendarapp.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.academy.Calendarapp.dto.LoginRq;
import ru.academy.Calendarapp.dto.SignUpRq;
import ru.academy.Calendarapp.dto.UserInfoDto;
import ru.academy.Calendarapp.entity.User;
import ru.academy.Calendarapp.mapper.UserMapper;
import ru.academy.Calendarapp.service.UserService;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    private final UserMapper mapper;

    public UserInfoDto createUser(SignUpRq sign) {
        User user = userService.createUser(sign.getEmail(),
                sign.getPassword(),
                sign.getName());
        return mapper.mapUserToUserInfoDto(user);
    }

    public String loginUser(LoginRq loginRq) {
        return userService.loginUser(loginRq.getEmail(), loginRq.getPassword());
    }
}
