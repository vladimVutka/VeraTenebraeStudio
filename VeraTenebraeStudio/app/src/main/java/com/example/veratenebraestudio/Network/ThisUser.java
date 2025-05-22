package com.example.veratenebraestudio.Network;

import DTOs.UserDTO;
import DataFiles.UserEntity;

public class ThisUser {
    private static ThisUser instance;
    private UserEntity currentUser;
    private UserDTO currentUserDTO;
    private ThisUser() {}

    public static synchronized ThisUser getInstance() {
        if (instance == null) {
            instance = new ThisUser();
        }
        return instance;
    }


    public void setCurrentUser(UserEntity user) {
        this.currentUser = user;
    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUserDTO(UserDTO user) {
        this.currentUserDTO = user;
    }

    public UserDTO getCurrentUserDTO() {
        return currentUserDTO;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void clearSession() {
        currentUser = null;
    }
}
