package com.example.contact.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contact.entity.User;

import java.util.List;

/**
 * @author: YH
 * @date: 2021/12/6
 * @desc:
 */
@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE user_name = :name AND password = :password  LIMIT 1")
    User getUser(String name, String password);

}
