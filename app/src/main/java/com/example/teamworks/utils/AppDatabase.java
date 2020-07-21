package com.example.teamworks.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.teamworks.model.UserList;

@Database(entities = {UserList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO userDao();
}