package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String postId; // Идентификатор поста, к которому привязан комментарий
    private String authorId; // Идентификатор автора комментария
    private String text; // Текст комментария
    private LocalDateTime commentedAt; // Время комментирования

    // Конструкторы, геттеры и сеттеры
}
