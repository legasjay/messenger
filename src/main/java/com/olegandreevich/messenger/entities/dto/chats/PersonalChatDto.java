package com.olegandreevich.messenger.entities.dto.chats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalChatDto implements BaseChatDto {
    private LocalDateTime createdAt;
    private boolean isActive;
    private List<ParticipantInfo> participants;

    // Геттеры и сеттеры

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean isIsActive() {
        return isActive;
    }

    @Override
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<ParticipantInfo> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantInfo> participants) {
        this.participants = participants;
    }
}
