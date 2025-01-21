package com.olegandreevich.messenger.entities.communities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "violations")
public class Violation {
    @Id
    private String id;
    private String violatorId; // Идентификатор нарушителя
    private String violatedRule; // Нарушенное правило
    private String penalty; // Наказание за нарушение правила
    private LocalDateTime reportedAt; // Время сообщения о нарушении

    // Конструкторы, геттеры и сеттеры
}
