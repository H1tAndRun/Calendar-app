package ru.academy.Calendarapp.dto;

import lombok.Data;

@Data
public class SignUpRq {

    private Long id;

    private String email;

    private String password;

    private String name;
}
