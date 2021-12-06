package com.example.contact.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contact.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "app_db";
    private static AppDataBase instance;

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, DATABASE_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

    public abstract UserDao userDao();

}
