package com.olegandreevich.messenger.entities.chats;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "read_statuses")
public class ReadStatus {
    @Id
    private String id;
    private String messageId; // Идентификатор сообщения
    private String userId; // Идентификатор пользователя
    private boolean isRead; // Прочтено ли сообщение данным пользователем

    // Конструкторы, геттеры и сеттеры
}
