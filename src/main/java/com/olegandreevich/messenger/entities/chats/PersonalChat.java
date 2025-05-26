package com.olegandreevich.messenger.entities.chats;

import com.olegandreevich.messenger.dto.chats.ParticipantInfo;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "personal_chats")
public class PersonalChat extends Chat {
    private List<ParticipantInfo> participants; // Список участников с основной информацией (должен содержать ровно два идентификатора)

    // Реализация абстрактных методов
    @Override
    public List<ParticipantInfo> getParticipants() {
        return participants;
    }

    @Override
    public void addParticipant(ParticipantInfo participant) {
        if (participants.size() == 2) {
            throw new IllegalStateException("Personal chat can have only two participants");
        } else {
            participants.add(participant);
        }
    }

    @Override
    public void removeParticipant(ParticipantInfo participant) {
        participants.remove(participant);
    }

    // Конструктор, геттеры и сеттеры
}
