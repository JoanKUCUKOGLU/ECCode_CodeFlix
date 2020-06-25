package com.codflix.backend.utils;

import com.codflix.backend.models.User;

public class GlobalData {
    private static GlobalData instance;

    private User currentUser;

    private GlobalData() {
    }

    public static synchronized GlobalData getInstance() {
        if(instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }
}
