package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "blocked_users")
public class BlockedUser {
    @Id
    private String id;
    private String userId; // Идентификатор блокирующего пользователя
    private String blockedUserId; // Идентификатор заблокированного пользователя
    private LocalDateTime blockedAt; // Время блокировки

    // Конструкторы, геттеры и сеттеры
}
