package com.olegandreevich.messenger.entities.chats;

import com.olegandreevich.messenger.dto.chats.ParticipantInfo;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "group_chats")
public class GroupChat extends Chat {
    private String name; // Название чата
    private List<ParticipantInfo> participants; // Список участников с основной информацией
    private List<ParticipantInfo> admins; // Список администраторов с основной информацией
    private boolean isPublic; // Является ли чат публичным (если нет, то доступ ограничен)

    // Реализация абстрактных методов
    @Override
    public List<ParticipantInfo> getParticipants() {
        return participants;
    }

    @Override
    public void addParticipant(ParticipantInfo participant) {
        participants.add(participant);
    }

    @Override
    public void removeParticipant(ParticipantInfo participant) {
        participants.remove(participant);
    }

    // Конструктор, геттеры и сеттеры

}
