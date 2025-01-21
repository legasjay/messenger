package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "announcements")
public class Announcement {
    @Id
    private String id;
    private String title; // Заголовок объявления
    private String content; // Содержимое объявления
    private String announcerId; // Идентификатор пользователя, сделавшего объявление
    private LocalDateTime announcedAt; // Время анонса

    // Конструкторы, геттеры и сеттеры
}
