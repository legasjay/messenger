package com.olegandreevich.messenger.entities.enums;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "friends")
public class Friend {
    @Id
    private String id;
    private String userId; // Идентификатор первого пользователя
    private String friendId; // Идентификатор второго пользователя
    private FriendStatus status; // Статус дружбы (REQUESTED, ACCEPTED, REJECTED)
    private LocalDateTime requestedAt; // Время запроса на дружбу

    // Конструкторы, геттеры и сеттеры
}
