package com.olegandreevich.messenger.entities.chats;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatId; // Идентификатор чата, в рамках которого отправлено сообщение
    private String senderId; // Идентификатор отправителя
    private String content; // Содержимое сообщения (текст, ссылка на файл и т.д.)
    private TrayIcon.MessageType type; // Тип сообщения (TEXT, IMAGE, FILE и т.д.)
    private LocalDateTime sentAt; // Время отправки сообщения
    private boolean read; // Прочитано ли сообщение получателями
    private Map<String, Boolean> readStatuses; // Карта статусов прочтения для каждого участника чата

    // Конструкторы, геттеры и сеттеры
}
