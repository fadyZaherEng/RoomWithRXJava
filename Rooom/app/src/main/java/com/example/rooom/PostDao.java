package com.example.rooom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
interface PostDao {
    @Insert
    Completable insertPost(Post post);

    @Query("select * from Post_table")
    Single<List<Post>> getPosts();

    @Delete
    Completable DeletePost(Post post);

    @Delete
    Completable AllDeletePost(List<Post> post);

    @Query("update Post_table set title=:Title where id=:Id")
    Completable UpdatePost(String Title,int Id);
}
