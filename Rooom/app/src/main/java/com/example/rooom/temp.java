package com.example.rooom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "temp")
public class temp
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    public String name;

    public temp(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
