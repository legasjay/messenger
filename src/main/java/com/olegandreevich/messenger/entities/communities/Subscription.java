package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "subscriptions")
public class Subscription {
    @Id
    private String id;
    private String subscriberId; // Идентификатор подписчика
    private String subscribedCommunityId; // Идентификатор сообщества, на которое оформлена подписка
    private LocalDateTime subscribedAt; // Дата оформления подписки

    // Конструкторы, геттеры и сеттеры
}
