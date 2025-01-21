package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "blacklists")
public class Blacklist {
    @Id
    private String id;
    private String blacklistedUserId; // Идентификатор заблокированного пользователя
    private String communityId; // Идентификатор сообщества, в котором установлен бан
    private LocalDateTime bannedAt; // Время наложения бана

    // Конструкторы, геттеры и сеттеры
}
