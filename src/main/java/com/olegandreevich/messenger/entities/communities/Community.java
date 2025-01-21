package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "communities")
public class Community {
    @Id
    private String id;
    private String name; // Название сообщества
    private String description; // Описание сообщества
    private List<String> members; // Список участников сообщества
    private List<Post> posts; // Список постов в сообществе
    private boolean isPrivate; // Является ли сообщество приватным
    private String creatorId; // Идентификатор создателя сообщества
    private LocalDateTime creationDate; // Дата создания сообщества

    // Конструкторы, геттеры и сеттеры
}
