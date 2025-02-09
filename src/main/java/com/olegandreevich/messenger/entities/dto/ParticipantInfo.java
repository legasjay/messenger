package com.olegandreevich.messenger.entities.dto;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ParticipantInfo {
    private String userId;
    private String username;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
