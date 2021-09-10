package com.example.rooom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>
{
    private Context context;
    private List<Post> Posts=new ArrayList<>();

    public Adapter(Context context) {
        this.context = context;
    }

    public void setPosts(List<Post> posts) {
        Posts = posts;
        notifyDataSetChanged();
    }

    public void setPost(Post post)
    {
        Posts.add(post);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_row,null,false);
        viewHolder holder=new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
       holder.title.setText(Posts.get(position).getUser().getName());
    }

    @Override
    public int getItemCount() {
        return Posts.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.RVTitle);
        }
    }
}
