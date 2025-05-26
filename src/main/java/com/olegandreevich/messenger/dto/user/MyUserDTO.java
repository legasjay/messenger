package com.olegandreevich.messenger.dto.user;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class MyUserDTO {

    private String username;

    private String password; // Хэш пароля

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

