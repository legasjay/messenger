package com.olegandreevich.messenger.entities.chats;

import com.olegandreevich.messenger.entities.dto.chats.ParticipantInfo;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
