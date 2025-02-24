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
    private MessageType type; // Тип сообщения
    private Instant sentAt; // Время отправки сообщения
    private Map<String, Boolean> readStatuses; // Карта статусов прочтения для каждого участника чата
    private String quotedMessageId; // Идентификатор цитируемого сообщения

    // Конструкторы, геттеры и сеттеры

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }

    public Map<String, Boolean> getReadStatuses() {
        return readStatuses;
    }

    public void setReadStatuses(Map<String, Boolean> readStatuses) {
        this.readStatuses = readStatuses;
    }

    public String getQuotedMessageId() {
        return quotedMessageId;
    }

    public void setQuotedMessageId(String quotedMessageId) {
        this.quotedMessageId = quotedMessageId;
    }
}
