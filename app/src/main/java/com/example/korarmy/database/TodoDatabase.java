package com.example.korarmy.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version =  1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}