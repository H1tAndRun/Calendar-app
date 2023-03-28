package ru.academy.Calendarapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.academy.Calendarapp.entity.User;
import ru.academy.Calendarapp.exception.PasswordIncorrectException;
import ru.academy.Calendarapp.exception.UserNotFoundException;
import ru.academy.Calendarapp.repository.UserRepository;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final String TOKEN_SUFFIX = "1234";

    private final String TOKEN_PREFIX = "73aa";

    public User createUser(String email, String password, String name) {
        return userRepository.save(User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build());
    }

    public String loginUser(String email, String password) {
        User userLog = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        if (!Objects.equals(userLog.getPassword(), password)) {
            throw new PasswordIncorrectException("Incorrect password");
        }
        return TOKEN_SUFFIX + userLog.getName() + TOKEN_PREFIX;
    }

    public User getUserByToken(String token) {
        String userName = token.replace(TOKEN_SUFFIX, "");
        userName=userName.replace(TOKEN_PREFIX, "");
        return userRepository.findUserByName(userName)
                .orElseThrow(() -> new UserNotFoundException(token));
    }
}
