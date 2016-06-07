package com.example.vacuumtubee.finalapproach;

/**
 * Created by Vacuum Tubee on 6/6/2016.
 */
public class UserDatabase {
    String username;
    String userType;
    String userId;

    public UserDatabase(String userType, String username, String userId) {
        this.userType = userType;
        this.username = username;
        this.userId = userId;
    }
}
