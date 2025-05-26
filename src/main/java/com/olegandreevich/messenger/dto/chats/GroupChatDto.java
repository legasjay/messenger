package com.olegandreevich.messenger.dto.chats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupChatDto implements BaseChatDto {
    private LocalDateTime createdAt;
    private boolean isActive;
    private String name;
    private List<ParticipantInfo> participants;
    private List<ParticipantInfo> admins;
    private boolean isPublic;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParticipantInfo> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantInfo> participants) {
        this.participants = participants;
    }

    public List<ParticipantInfo> getAdmins() {
        return admins;
    }

    public void setAdmins(List<ParticipantInfo> admins) {
        this.admins = admins;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
