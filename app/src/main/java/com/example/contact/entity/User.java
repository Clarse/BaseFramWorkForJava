package com.example.contact.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int Uid;
    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "password")
    private String password;

    public User() {}

    @Ignore
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getUid() {
        return Uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
