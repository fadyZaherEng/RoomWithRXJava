package com.example.rooom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities ={Post.class,temp.class},version = 1)
@TypeConverters(RoomConverter.class)
public abstract class PostRoomDatabase extends RoomDatabase
{
    private static PostRoomDatabase INSTANCE;
    public abstract PostDao postDao();

    public static synchronized PostRoomDatabase getINSTANCE(Context context)
    {
        if (INSTANCE==null){
            INSTANCE=Room.databaseBuilder(context.getApplicationContext(),
                    PostRoomDatabase.class, "posts_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
