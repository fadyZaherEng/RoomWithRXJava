package com.example.rooom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView Posts;
    Adapter adapter;
    Button add,get;
    EditText title,body;
    private PostRoomDatabase postsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.insert);
        get=findViewById(R.id.Get);

        title=findViewById(R.id.title);
        body=findViewById(R.id.body);

        Posts=findViewById(R.id.Rv);
        adapter =new Adapter(this);
        Posts.setLayoutManager(new LinearLayoutManager(this));
        Posts.setAdapter(adapter);

       postsDatabase=PostRoomDatabase.getINSTANCE(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsDatabase.postDao().insertPost(
                new Post(new User(1,"XXX"), title.getText().toString(), body.getText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(MainActivity.this, "Added Succesfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             postsDatabase.postDao().getPosts()
                     .subscribeOn(Schedulers.computation())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new SingleObserver<List<Post>>() {
                         @Override
                         public void onSubscribe(@NonNull Disposable d) {

                         }

                         @Override
                         public void onSuccess(@NonNull List<Post> posts) {
                            adapter.setPosts(posts);
                         }

                         @Override
                         public void onError(@NonNull Throwable e) {

                         }
                     });
            }
        });
    }

}