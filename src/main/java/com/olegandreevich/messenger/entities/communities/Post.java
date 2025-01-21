package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String communityId; // Идентификатор сообщества, к которому привязан пост
    private String authorId; // Идентификатор автора поста
    private String content; // Содержимое поста (текст, ссылка на файл и т.д.)
    private List<Comment> comments; // Список комментариев к посту
    private int likesCount; // Количество лайков к посту
    private LocalDateTime postedAt; // Время публикации поста

    // Конструкторы, геттеры и сеттеры
}
