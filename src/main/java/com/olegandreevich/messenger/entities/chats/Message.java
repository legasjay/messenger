package com.olegandreevich.messenger.entities.chats;

import com.olegandreevich.messenger.entities.enums.MessageType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatId; // Идентификатор чата, в рамках которого отправлено сообщение
    private String senderId; // Идентификатор отправителя
    private Object content; // Универсальное содержимое сообщения (текст, ссылки на файлы и т.д.)
    private MessageType type; // Тип сообщения (создай свой enum или класс для типов сообщений)
    private Instant sentAt; // Время отправки сообщения
    private Map<String, Boolean> readStatuses; // Карта статусов прочтения для каждого участника чата
    private String quotedMessageId; // Идентификатор цитируемого сообщения

    // Конструкторы, геттеры и сеттеры
}
