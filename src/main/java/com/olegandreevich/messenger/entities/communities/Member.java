package com.olegandreevich.messenger.entities.communities;

import com.olegandreevich.messenger.entities.enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "members")
public class Member {
    @Id
    private String id;
    private String memberId; // Идентификатор участника
    private String communityId; // Идентификатор сообщества
    private Role role; // Роль участника в сообществе (PARTICIPANT, MODERATOR, ADMIN)
    private LocalDateTime joinedAt; // Дата вступления в сообщество

    // Конструкторы, геттеры и сеттеры
}
