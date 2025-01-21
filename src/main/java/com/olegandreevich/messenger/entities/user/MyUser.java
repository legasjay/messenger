package com.olegandreevich.messenger.entities.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class MyUser {

    @Id
    private String id;

    private String username;

    private String password; // Хэш пароля

    private String email;

    @DBRef(lazy = true)
    private List<Profile> profiles;

    // Геттеры и сеттеры
}
