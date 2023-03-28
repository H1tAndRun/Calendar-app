package ru.academy.Calendarapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.academy.Calendarapp.dto.LoginRq;
import ru.academy.Calendarapp.dto.SignUpRq;
import ru.academy.Calendarapp.dto.UserInfoDto;
import ru.academy.Calendarapp.facade.UserFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/signup")
    public ResponseEntity<UserInfoDto> createUser(@RequestBody SignUpRq sign) {
        return ResponseEntity.ok(userFacade.createUser(sign));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRq loginRq) {
        return ResponseEntity.ok(userFacade.loginUser(loginRq));
    }
}
