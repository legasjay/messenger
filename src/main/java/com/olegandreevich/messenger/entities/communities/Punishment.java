package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "punishments")
public class Punishment {
    @Id
    private String id;
    private String punishedUserId; // Идентификатор пользователя, которого наказывают
    private String punishmentType; // Тип наказания (BAN, WARNING, FINE)
    private String duration; // Продолжительность наказания
    private LocalDateTime issuedAt; // Время выдачи наказания

    // Конструкторы, геттероры и сеттеры
}
