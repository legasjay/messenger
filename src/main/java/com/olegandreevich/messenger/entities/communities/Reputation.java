package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reputations")
public class Reputation {
    @Id
    private String id;
    private String reputationHolderId; // Идентификатор пользователя, который владеет репутацией
    private String actionPerformedById; // Идентификатор пользователя, который совершил действие, влияющее на репутацию
    private String actionDescription; // Описание действия, повлиявшего на репутацию
    private LocalDateTime recordedAt; // Время регистрации действия

    // Конструкторы, геттеры и сеттеры
}
