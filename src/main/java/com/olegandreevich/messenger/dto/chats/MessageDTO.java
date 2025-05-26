package com.olegandreevich.messenger.dto.chats;

import com.olegandreevich.messenger.entities.enums.MessageType;

import java.time.Instant;
import java.util.Map;

public class MessageDTO {

    private String senderId; // Идентификатор отправителя
    private Object content; // Универсальное содержимое сообщения (текст, ссылки на файлы и т.д.)
    private MessageType type; // Тип сообщения
    private Instant sentAt; // Время отправки сообщения
    private Map<String, Boolean> readStatuses; // Карта статусов прочтения для каждого участника чата
    private String quotedMessageId; // Идентификатор цитируемого сообщения


}
