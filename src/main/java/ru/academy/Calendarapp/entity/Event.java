package ru.academy.Calendarapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @Column
    private LocalDate date;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;
}
