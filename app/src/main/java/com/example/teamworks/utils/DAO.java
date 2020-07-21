package com.example.teamworks.utils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.teamworks.model.UserList;

import java.util.List;

@Dao
public interface DAO {
    @Query("SELECT * FROM UserList")
    List<UserList> getAll();

    @Query("SELECT * FROM UserList where email= :mail and password= :password")
    UserList getUser(String mail, String password);

    @Insert
    void insert(UserList user);
}
