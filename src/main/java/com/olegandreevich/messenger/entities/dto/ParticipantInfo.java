package com.olegandreevich.messenger.entities.dto;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ParticipantInfo {
    private ObjectId userId;
    private String username;
}
