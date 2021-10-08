package com.example.korarmy.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 2, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();
    private static TodoDatabase instance = null;

    public static synchronized TodoDatabase getInstance(Context context){
        if(instance == null){
                instance =  Room.databaseBuilder(context.getApplicationContext(),
                        TodoDatabase.class, "todo_Database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}