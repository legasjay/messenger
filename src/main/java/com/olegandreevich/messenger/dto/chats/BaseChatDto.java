package com.olegandreevich.messenger.dto.chats;

import java.time.LocalDateTime;

public interface BaseChatDto {
    LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime createdAt);
    boolean isIsActive();
    void setIsActive(boolean isActive);
}
