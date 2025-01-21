package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "mentions")
public class Mention {
    @Id
    private String id;
    private String mentionedUserId; // Идентификатор упомянутого пользователя
    private String mentionerId; // Идентификатор пользователя, который сделал упоминание
    private String objectId; // Идентификатор объекта, в котором сделано упоминание
    private LocalDateTime mentionedAt; // Время упоминания

    // Конструкторы, геттеры и сеттеры
}
