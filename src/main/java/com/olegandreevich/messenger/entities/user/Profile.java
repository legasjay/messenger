package com.olegandreevich.messenger.entities.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profiles")
public class Profile {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String birthDate;

    private byte[] avatar;

    private String description;

    private String userId; // ID пользователя, которому принадлежит профиль

    // Геттеры и сеттеры
}
