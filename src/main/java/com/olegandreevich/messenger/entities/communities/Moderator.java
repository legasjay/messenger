package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "moderators")
public class Moderator {
    @Id
    private String id;
    private String moderatorId; // Идентификатор модератора
    private String communityId; // Идентификатор сообщества, в котором модератор имеет полномочия
    private LocalDateTime appointedAt; // Время назначения модератором

    // Конструкторы, геттеры и сеттеры
}
