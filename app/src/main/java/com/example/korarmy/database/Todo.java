package com.example.korarmy.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String todo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Todo(String todo) {
        this.todo = todo;
    }

    @Override
    public String toString(){
        final StringBuffer sb = new StringBuffer("");
        sb.append(todo);
        return sb.toString();
    }
}