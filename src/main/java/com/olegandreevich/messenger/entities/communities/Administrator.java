package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "administrators")
public class Administrator {
    @Id
    private String id;
    private String administratorId; // Идентификатор администратора
    private String communityId; // Идентификатор сообщества, в котором администратор имеет полномочия
    private LocalDateTime appointedAt; // Время назначения администратором

    // Конструкторы, геттеры и сеттеры
}
