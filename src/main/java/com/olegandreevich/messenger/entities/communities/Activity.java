package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String activityType; // Тип активности (POST_CREATED, COMMENT_ADDED, LIKE_RECEIVED)
    private String actorId; // Идентификатор пользователя, совершившего действие
    private String objectId; // Идентификатор объекта, к которому относится активность
    private LocalDateTime occurredAt; // Время совершения действия

    // Конструкторы, геттеры и сеттеры
}
