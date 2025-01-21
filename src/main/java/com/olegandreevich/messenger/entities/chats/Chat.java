package com.olegandreevich.messenger.entities.chats;

import com.olegandreevich.messenger.entities.dto.ParticipantInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "chats")
public abstract class Chat {
    @Id
    protected String id;
    protected LocalDateTime createdAt; // Время создания чата
    protected boolean isActive; // Активен ли чат (может быть закрыт одним из участников)

    // Абстрактные методы для реализации в наследниках
    public abstract List<ParticipantInfo> getParticipants();
    public abstract void addParticipant(ParticipantInfo participant);
    public abstract void removeParticipant(ParticipantInfo participant);
}
