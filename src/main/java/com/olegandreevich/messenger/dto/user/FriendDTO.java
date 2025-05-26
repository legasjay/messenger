package com.olegandreevich.messenger.dto.user;

import com.olegandreevich.messenger.entities.enums.FriendStatus;

import java.time.LocalDateTime;

public class FriendDTO {
    private String userId; // Идентификатор первого пользователя
    private String friendId; // Идентификатор второго пользователя
    private FriendStatus status; // Статус дружбы (REQUESTED, ACCEPTED, REJECTED)
    private LocalDateTime requestedAt; // Время запроса на дружбу
}
