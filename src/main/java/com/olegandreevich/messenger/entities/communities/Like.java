package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "likes")
public class Like {
    @Id
    private String id;
    private String likedObjectId; // Идентификатор объекта, который получил лайк
    private String likerId; // Идентификатор пользователя, который поставил лайк
    private LocalDateTime likedAt; // Время, когда был поставлен лайк

    // Конструкторы, геттеры и сеттеры
}
