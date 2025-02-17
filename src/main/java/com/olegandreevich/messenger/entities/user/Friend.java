package com.olegandreevich.messenger.entities.user;

import com.olegandreevich.messenger.entities.enums.FriendStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "friends")
public class Friend {
    @Id
    private String id;
    private String userId; // Идентификатор первого пользователя
    private String friendId; // Идентификатор второго пользователя
    private FriendStatus status; // Статус дружбы (REQUESTED, ACCEPTED, REJECTED)
    private LocalDateTime requestedAt; // Время запроса на дружбу

    // Конструкторы, геттеры и сеттеры

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public FriendStatus getStatus() {
        return status;
    }

    public void setStatus(FriendStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}
