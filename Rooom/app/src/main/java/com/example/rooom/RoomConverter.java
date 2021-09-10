package com.example.rooom;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class RoomConverter
{
    @TypeConverter
   public String FromUserToString(User user)
   {
       return new Gson().toJson(user);
   }
   @TypeConverter
   public User FromStringToUser(String user)
   {
       return new Gson().fromJson(user,User.class);
   }
}
