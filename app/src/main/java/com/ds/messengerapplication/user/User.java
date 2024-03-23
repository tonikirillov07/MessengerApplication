package com.ds.messengerapplication.user;

import java.util.Objects;

public class User {
    private final String mail;
    private final String password;
    private final String userId;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
        this.userId = Objects.requireNonNull(UserController.getInstance().getFirebaseAuth().getCurrentUser()).getUid();
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }
}
